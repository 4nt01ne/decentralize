package be.ewils.decentralize.model;

import java.net.URI;

/**
 *
 * @author antoine
 */
public class RemoteAccount {

    private final URI remoteResource;
    private final String login;
    private String pasword;

    /**
     *
     * @param remoteResourceUri the URI where to connect to the remote account
     * @param login the login to use to authenticate at the remote resource
     * @throws IllegalArgumentException if the remoteResourceUri is not a valid
     * URI
     */
    public RemoteAccount(String remoteResourceUri, String login) {
        this.remoteResource = URI.create(remoteResourceUri);
        this.login = login;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public URI getRemoteResource() {
        return remoteResource;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "RemoteAccount{" + ", login=" + login + "remoteResource=" + remoteResource + '}';
    }

}
