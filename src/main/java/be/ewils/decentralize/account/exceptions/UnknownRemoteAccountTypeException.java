package be.ewils.decentralize.account.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Antoine
 * Date: 8/8/13
 * Time: 11:42 PM

 */
public class UnknownRemoteAccountTypeException extends ApplicationInternalException {

    /**
     * constructs an ApplicationInternalException telling the the provided remote account type is not known
     * @param remoteAccountType the remote account type
     */
    public UnknownRemoteAccountTypeException(String remoteAccountType) {

        super("could not find a remote account of type '" + remoteAccountType + "'");
    }
}
