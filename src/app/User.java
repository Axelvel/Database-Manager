package app;

public class User {

    private int id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private boolean status;

    public User(int id, String username, String password, String name, String lastname, boolean status) {
        this.id = id;
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
        return this.name + " " + this.lastname;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }


}
