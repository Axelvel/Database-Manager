package app;

import java.util.ArrayList;
import java.util.List;

public class Users {


    private List<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(int id) {
        users.removeIf(user -> (user.getId() == id));
    }

    public void showUserslist() {
        this.users.forEach(user -> {
            System.out.println(user.getName());
        });
    }

}
