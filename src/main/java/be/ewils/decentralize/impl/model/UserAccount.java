package be.ewils.decentralize.impl.model;

/**
 *
 * @author antoine
 */
public class UserAccount {

    private long seed;
    private final String pseudo;
    private final String email;
    private String firstName;
    private String lastName;

    public UserAccount(String pseudo, String email) {
        this.seed = System.nanoTime();
        this.pseudo = pseudo;
        this.email = email;
    }

    public String getId() {
        return seed + "-" + pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "UserAccount{" + "pseudo=" + pseudo + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
}
