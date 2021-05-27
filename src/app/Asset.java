package app;

public class Asset {

    private int code;
    private int type;
    private String name;
    private boolean status;
    private boolean available;

    public Asset(int code, int type, String name, boolean status, boolean available) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.status = status;
        this. available = available;
    }

    //Getters

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.status;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void showAsset() {
        System.out.println("Code : " + this.getCode() + ", Name : " + this.getName());
    }

    //Setters
    
}
