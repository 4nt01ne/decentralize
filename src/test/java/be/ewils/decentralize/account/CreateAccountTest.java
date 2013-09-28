/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.ewils.decentralize.account;

import be.ewils.decentralize.model.Account;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author antoine
 */
public class CreateAccountTest {
    
    private Account account;
    
    @Test
    public void joinTest() {
        account = new Account();
        account.setPseudo("pseudo");
        account.setEmail("somebody@example.com");
        account.setFirstName("Eddy");
        account.setLastName("Merckx");
        
        Assert.assertNotNull(account.getId());
        Assert.assertEquals("pseudo", account.getPseudo());
        Assert.assertEquals("somebody@example.com", account.getEmail());
        Assert.assertEquals("Eddy", account.getFirstName());
        Assert.assertEquals("Merckx", account.getLastName());
    }    
}
