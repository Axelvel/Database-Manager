package app;

public class User {

    private int id;
    private String name;
    private String lastname;
    private boolean status;

    public User(int id, String name, String lastname, boolean status) {
        this.id = id;
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


}
