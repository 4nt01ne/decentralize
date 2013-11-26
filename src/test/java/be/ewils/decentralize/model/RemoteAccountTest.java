/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ewils.decentralize.model;

import java.net.URI;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author antoine
 */
public class RemoteAccountTest {

    RemoteAccount account;
    
    @Test
    public void testCreation() {
        account = new RemoteAccount("http://somewhere.com", "login");
        account.setPasword("password");
        Assert.assertEquals(URI.create("http://somewhere.com"), account.getRemoteResource());
        Assert.assertEquals("login", account.getLogin());
        Assert.assertEquals("password", account.getPasword());
    }
}
