package app;

/**
 * Computer class
 * Each computer has an os, a brand, a memory and a ram
 */
public class Computer extends Asset {

    private String os;
    private String brand;
    private int memory;
    private int ram;

    public Computer(String code, Type type, String status, boolean available,String brand,
                    String os, int memory, int ram) {
        super(code, type, status, available);
        this.brand = brand;
        this.os = os;
        this.memory = memory;
        this.ram = ram;
    }

    @Override
    public void showAsset() {
        super.showAsset();
        System.out.println("\nCharacteristics :\nBrand : " + this.getBrand() + "\nOS : " + this.os + "\nMemory : "
                + this.memory + "\nRAM : " + this.ram);
    }

    @Override
    public String getOs(){ return this.os; }
    public String getBrand(){return this.brand;}
    public int getMemory(){return this.memory;}
    public int getRam(){return this.ram;}


}
