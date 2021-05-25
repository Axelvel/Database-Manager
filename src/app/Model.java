package app;

public class Model {

    public Users users = new Users();
    public Database database = new Database();


    public Model() {

        //Creating new users
        User user1 = new User(1, "abc", "pass", "Jean", "Robert", true);
        User user2 = new User(2, "xyz", "word", "Jeanne", "Roberta", true);

        //Creating new assets
        Computer comp1 = new Computer(1, Type.COMPUTER,"Computer1", true,true, "Window10", 1024, 16);
        Asset asset1 = new Asset(2,Type.COMPUTER, "Asset2", true, false);

        users.addUser(user1);
        users.addUser(user2);

        database.addAsset(comp1);
        database.addAsset(asset1);

    }

}
