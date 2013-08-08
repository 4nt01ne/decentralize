package be.ewils.decentralize.account;

import be.ewils.decentralize.account.exceptions.ApplicationInternalException;

/**
 * Registers a remote account in the user account
 *
 * Created with IntelliJ IDEA.
 * User: Antoine
 * Date: 8/8/13
 * Time: 11:33 PM
 */
public interface RegisterRemoteAccount {

    /**
     * registers a remote account of the given type
     * @param user the identifier of the user that want to register the remote account
     * @param remoteAccountType the type of account to register
     * */
    void register(final String user, final String remoteAccountType) throws ApplicationInternalException;
}
