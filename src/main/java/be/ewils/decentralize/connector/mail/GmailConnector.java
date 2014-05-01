package be.ewils.decentralize.connector.mail;

import be.ewils.decentralize.model.Email;
import be.ewils.decentralize.model.RemoteAccount;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antoine
 */
public class GmailConnector {

    /**
     * *************************************************************************
     * public static fields
     * ************************************************************************
     */
    public static final String GMAIL_DOMAIN = "@gmail.com";

    /**
     * *************************************************************************
     * private static fields
     * ************************************************************************
     */
    private static final Properties properties = new Properties();
    private static final Logger LOG = LoggerFactory.getLogger(GmailConnector.class.getName());

    static {
        /**
         * mail.store.protocol, mail.transport.protocol, mail.host, mail.user,
         * and mail.from
         */
        properties.setProperty("mail.store.protocol", "imaps");
    }

    /**
     * *************************************************************************
     * public methods
     * ************************************************************************
     */
    public void send(RemoteAccount account, Email email)
        throws
        EmailException {

        org.apache.commons.mail.Email _email = new SimpleEmail();
        _email.setHostName(account.getRemoteResource().getHost());
        _email.setSmtpPort(account.getRemoteResource().getPort());
        _email.setAuthenticator(new DefaultAuthenticator(account.getLogin(), account.getPasword()));
        _email.setSSLOnConnect(true);
        _email.setFrom(account.getLogin() + GMAIL_DOMAIN);
        _email.setSubject(email.getSubject());
        _email.setMsg(email.getBody());
        for (String destination : email.getDestinations()) {
            _email.addTo(destination);
        }
        for (String cc : email.getDestinationsInCopy()) {
            _email.addCc(cc);
        }
        for (String bcc : email.getDestinationsInBlackCopy()) {
            _email.addTo(bcc);
        }

        _email.send();
    }

    public List<Email> readInbox(RemoteAccount account) 
        throws
        NoSuchProviderException,
        MessagingException,
        IOException {
        
        return readInbox(account, -1);
    }
    
    public List<Email> readInbox(RemoteAccount account, int maxMails)
        throws
        NoSuchProviderException,
        MessagingException,
        IOException {

        List<Email> emails = new LinkedList<>();;
        Store store = null;
        Folder inbox = null;


        try {
            Session session = Session.getDefaultInstance(properties, null);
            store = session.getStore();
            store.connect(account.getRemoteResource().toString(), account.getLogin() + GMAIL_DOMAIN, account.getPasword());
            inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();
            maxMails = (maxMails <= 0)? messageCount: maxMails;
            messageCount = (messageCount < maxMails) ? messageCount : maxMails;
            Message[] messages = inbox.getMessages(1, messageCount);
            for (Message message : messages) {
                emails.add(toEmail(message));
            }
        } catch (IndexOutOfBoundsException e) {
            LOG.debug("no message found for " + account);
        } finally {
            close(store);
            close(inbox);
        }

        return emails;
    }


    /**
     * *************************************************************************
     * private methods
     * ************************************************************************
     */
    private void close(Store store) throws MessagingException {
        if (store != null) {
            store.close();
        }
    }
    private void close(Folder folder) throws MessagingException {
        if (folder != null && folder.isOpen()) {
            folder.close(false);
        }
    }

    private List<String> asAddressList(Message message, Message.RecipientType recipientType) 
    throws 
        MessagingException {
        
        Address[] recipients = Optional.
            fromNullable(message.getRecipients(recipientType)).
            or(new Address[0]);
            
        return toAddressList(recipients);
    }

    private List<String> toAddressList(Address[] addresses) {
        
        List<String> addressesList = new LinkedList<>();       
                
        for (Address recipient : addresses) {
            addressesList.add(recipient.toString());
        }
        
        return addressesList;
    }
    
    private Email toEmail(Message message) throws IOException, MessagingException {
        Email email = new Email();
        
        Object subject = Optional.
            fromNullable(message.getSubject()).
            or("");
        email.setSubject(subject.toString());        
        
        Object content = Optional.
            fromNullable(message.getContent()).
            or("");
        email.setBody(content);
        
        Address[] senders = Optional.
            fromNullable(message.getFrom()).
            or(new Address[0]);
        email.setSenders(toAddressList(senders));
        
        email.setDestinations(asAddressList(message, Message.RecipientType.TO));
        email.setDestinationsInCopy(asAddressList(message, Message.RecipientType.CC));
        email.setDestinationsInBlackCopy(asAddressList(message, Message.RecipientType.BCC));
        
        return email;
    }
}
