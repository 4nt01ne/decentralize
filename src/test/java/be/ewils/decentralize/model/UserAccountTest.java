package be.ewils.decentralize.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author antoine
 */
public class UserAccountTest {
    private UserAccount account;

    @Test
    public void testCreation() {
        account = new UserAccount("pseudo", "somebody@example.com");
        account.setFirstName("Eddy");
        account.setLastName("Merckx");

        Assert.assertNotNull(account.getId());
        Assert.assertEquals("pseudo", account.getPseudo());
        Assert.assertEquals("somebody@example.com", account.getEmail());
        Assert.assertEquals("Eddy", account.getFirstName());
        Assert.assertEquals("Merckx", account.getLastName());
    }
}
