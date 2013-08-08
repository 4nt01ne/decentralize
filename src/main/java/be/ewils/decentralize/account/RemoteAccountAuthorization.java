package be.ewils.decentralize.account;

/**
 * Asks for an authorization to the remote account service provider.
 *
 * Created with IntelliJ IDEA.
 * User: Antoine
 * Date: 8/9/13
 * Time: 12:00 AM
 */
public interface RemoteAccountAuthorization {

    /**
     * request the authorization to access the remote account
     * @param user the requesting user
     * @param remoteAccountType the type of remote account
     */
    void request(final String user, final String remoteAccountType);
}
