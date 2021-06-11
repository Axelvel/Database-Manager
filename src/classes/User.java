package classes;

/**
 * Class representing an user
 * Each user has a unique username, a name, a lastname and a password
 * isAdmin is true if the user is an admin, false if not
 */
public class User {
    private String username;
    private String name;
    private String lastname;
    private String password;
    private boolean isAdmin;

    public User(String username, String password, String name, String lastname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {return this.lastname;}

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
