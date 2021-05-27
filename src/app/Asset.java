package app;

public class Asset {

    private String code;
    private Type type;
    private String status;
    private boolean available;

    public Asset(String code, Type type, String status, boolean available) {
        this.code = code;
        this.type = type;
        this.status = status;
        this.available = available;
    }


    //Getters

    public String getCode() {
        return this.code;
    }

    public Type getType() {return this.type;}

    public String getStatus() {
        return this.status;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void showAsset() {
        System.out.println("Code : " + this.getCode());
    }

    //Setters

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    //VIRTUAL METHODS
    public String getBrand(){return null;}
    public String getOs(){return null;}
    public int getMemory(){return 0;}
    public int getRam(){return 0;}
    public String getSwitches(){return null;}
    public boolean getWireless(){return false;}
}
