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
}
