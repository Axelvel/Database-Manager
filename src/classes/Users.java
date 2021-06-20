package classes;

import java.util.ArrayList;
import java.util.List;

/**
 * class used in order to store the users found in the sqlite database
 */
public class Users {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(String username) {
        users.removeIf(user -> (user.getUsername().equals(username)));
    }

    public void showUserslist() {
        this.users.forEach(user -> System.out.println(user.getName()));
    }

    public String getUserUsername(String username) {

        for (User user : this.users) {
            if (user.getUsername().equals(username)) return user.getUsername();
        }
        return null;
    }

    public boolean passwordMatch(String username, String password) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) return true;
            }
        }
        return  false;
    }

    public int getUserIndex(String username) {
        for(int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username)) return users.indexOf(users.get(i));
        }
        return -1;
    }
}
