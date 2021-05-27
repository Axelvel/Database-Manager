package app;

public class Keyboard extends Asset{

    private String brand;
    private boolean wireless;
    private String switches;

    public Keyboard(String code, Type type, String status, boolean available,String brand, boolean wireless, String switches){
        super(code,type,status,available);
        this.brand = brand;
        this.wireless = wireless;
        this.switches = switches;
    }

    @Override
    public void showAsset() {
        System.out.println("Code : " + this.getCode() + ", brand : " + this.brand + ", switches: " + this.switches);
    }

    @Override
    public String getBrand(){return this.brand;}
    public String getSwitches(){return this.switches;}
    public boolean getWireless(){return this.wireless;}
}
