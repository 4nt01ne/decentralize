package be.ewils.decentralize.account.impl;

import be.ewils.decentralize.account.RegisterRemoteAccount;
import be.ewils.decentralize.account.RemoteAccountAuthorization;
import be.ewils.decentralize.account.RemoteAccountType;
import be.ewils.decentralize.account.exceptions.UnknownRemoteAccountTypeException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Antoine
 * Date: 8/8/13
 * Time: 11:22 PM
 */
public class RegisterRemoteAccountImpl implements RegisterRemoteAccount{

    /* ---------------------------------------------------------------------------------
    * private fields
    * --------------------------------------------------------------------------------- */
    //TODO register via white board pattern
    private final Map<String, RemoteAccountType> remoteAccountTypes = new HashMap<>();

    @Resource(name="remoteAccountAuthorization")
    private RemoteAccountAuthorization remoteAccountAuthorization;

    @Override
    public void register(String user, String remoteAccountType) throws UnknownRemoteAccountTypeException {

        final RemoteAccountType type = remoteAccountTypes.get(remoteAccountType);

        if(type == null) {
            throw new UnknownRemoteAccountTypeException(remoteAccountType);
        }

        remoteAccountAuthorization.request(user, remoteAccountType);
    }
}
