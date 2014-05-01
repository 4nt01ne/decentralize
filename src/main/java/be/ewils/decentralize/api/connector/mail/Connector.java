/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.ewils.decentralize.api.connector.mail;

import be.ewils.decentralize.impl.model.Email;
import be.ewils.decentralize.impl.model.RemoteAccount;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author antoine
 */
public interface Connector {

    List<Email> readInbox(RemoteAccount account) throws MessagingException, IOException;

    List<Email> readInbox(RemoteAccount account, int maxMails) throws MessagingException, IOException;

    /**
     * *************************************************************************
     * public methods
     * ************************************************************************
     */
    void send(RemoteAccount account, Email email) throws EmailException;
    
}
