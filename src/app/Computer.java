package app;

public class Computer extends Asset {

    private String os;
    private String brand;
    private int memory;
    private int ram;


    public Computer(String code, Type type, String status, boolean available,String brand, String os, int memory, int ram) {
        super(code, type, status, available);
        this.brand = brand;
        this.os = os;
        this.memory = memory;
        this.ram = ram;
    }

    @Override
    public void showAsset() {
        System.out.println("Code : " + this.getCode() + ", OS : " + this.os + ", Memory : " + this.memory + ", RAM : " + this.ram);
    }

    @Override
    public String getOs(){ return this.os; }
    public String getBrand(){ return this.brand; }
    public int getMemory(){ return this.memory; }
    public int getRam(){ return this.ram; }

    @Override
    public void setOs(String os) { this.os = os; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setMemory(int memory) { this.memory = memory; }
    public void setRam(int ram) { this.ram = ram; }

}
