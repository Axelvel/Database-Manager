package classes;

import java.util.ArrayList;
import java.util.List;

public class Users {


    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return this.users;
    }

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

    public int getUserId(String username) {

        for(int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username)) {
                return users.get(i).getId();
            }
        }
        return 0;
    }

    public boolean passwordMatch(int id, String password) {

        for (int i = 0; i < this.users.size(); i++) {
            if (users.get(i).getId() == id) {
                if (users.get(i).getPassword().equals(password)) {
                    return true;
                }
            }
        }

        return  false;

    }

}
