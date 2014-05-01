package be.ewils.decentralize.connector.mail;

import be.ewils.decentralize.impl.connector.mail.GmailConnector;
import be.ewils.decentralize.impl.model.Email;
import be.ewils.decentralize.impl.model.RemoteAccount;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import org.apache.commons.mail.EmailException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
    public void testSendAndReceive() throws EmailException, MessagingException, NoSuchProviderException, IOException {
        final RemoteAccount smtpAccount = new RemoteAccount("smtp://smtp.googlemail.com:465", "YOUR_LOGIN");
        smtpAccount.setPasword("YOUR_PASSWORD");
        
        final long now = System.currentTimeMillis();
        Email email = new Email();
        email.addDestination("YOUR_LOGIN@gmail.com");
        email.setSubject("test - " + now);
        email.setBody("test - " + now);
        connector.send(smtpAccount, email);
        
        final RemoteAccount imapAccount = new RemoteAccount("imap.gmail.com", "YOUR_LOGIN");
        imapAccount.setPasword("YOUR_PASSWORD");
        boolean found = false;
        List<Email> emails = connector.readInbox(imapAccount);
        for(Email newEmail: emails) {
            if(newEmail.getSubject().equals(newEmail.getSubject())) {
                Assert.assertEquals(email, newEmail);
                found = true;
            }
        }
        
        Assert.assertTrue(found);
    }    
 }
