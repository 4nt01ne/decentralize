package be.ewils.decentralize.impl.account.exceptions;

/**
 * Any exception raised to internal condition
 *
 * Created with IntelliJ IDEA.
 * User: Antoine
 * Date: 8/8/13
 * Time: 11:44 PM
 */
public class ApplicationInternalException extends Exception {

    /**
     * Constructs a new  internal exception with the specified detail message.
     * @param message the detail message
     */
    public ApplicationInternalException(String message) {

        super(message);
    }
}
