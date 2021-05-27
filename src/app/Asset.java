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
}
