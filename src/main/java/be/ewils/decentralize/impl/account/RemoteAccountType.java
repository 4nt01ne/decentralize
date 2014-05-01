package be.ewils.decentralize.impl.account;

/**
 *
 * A descriptor for one remote account type (ex: Google, Flikr, ...)
 *
 * Created with IntelliJ IDEA.
 * User: Antoine
 * Date: 8/8/13
 * Time: 11:28 PM
 */
public interface RemoteAccountType {

    /**
     * @return the identifier of the account type
     */
    String getId();

    /**
     * @return a description of the type of account
     */
    String getDescription();
}
