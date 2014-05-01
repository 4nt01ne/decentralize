package be.ewils.decentralize.connector.mail;

import be.ewils.decentralize.model.Email;
import be.ewils.decentralize.model.RemoteAccount;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import org.apache.commons.mail.EmailException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author antoine
 */
public class TestGmailConnector {

    GmailConnector connector;
    
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("------------- before TestGmailConnector ------------");        
    }
    
    @AfterClass
    public static void afterClass() {
        System.out.println("------------- after TestGmailConnector ------------");        
    }
    
    @Before
    public void beforeTest() {
        System.out.println("------------------ beforeTest ---------------------");        
        connector = new GmailConnector();
    }
    
    @After
    public void afterTest() {
        System.out.println("------------------ afterTest ---------------------");
    }
    
    @Test
    public void testSend() throws EmailException {
        final RemoteAccount remoteAccount = new RemoteAccount("smtp://smtp.googlemail.com:465", "YOUR_LOGIN");
        remoteAccount.setPasword("YOUR_PASSWORD");
        
        final long now = System.currentTimeMillis();
        Email email = new Email();
        email.addDestination("YOUR_ACCOUNT@gmail.com");
        email.setSubject("test - " + now);
        email.setBody("test - " + now);
        connector.send(remoteAccount, email);
    }    
    @Test
    public void testReadInbox() 
    throws 
        EmailException, 
        MessagingException, 
        NoSuchProviderException, 
        IOException {
        
        final RemoteAccount remoteAccount = new RemoteAccount("imap.gmail.com", "YOUR_LOGIN");
        remoteAccount.setPasword("YOUR_PASSWORD");
        
        List<Email> emails = connector.readInbox(remoteAccount, 5);
        System.out.println(emails);
    }    
}
