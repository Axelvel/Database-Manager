package classes;

public class User {
    private String username;
    private String password;
    private String name;
    private String lastname;
    private boolean status;

    public User(String username, String password, String name, String lastname, boolean status) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.status = status;
    }

    public boolean isAdmin() {
        return this.status;
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
