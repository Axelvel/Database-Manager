package app;

public class Computer extends Asset {

    private String os;
    int memory;
    int ram;


    public Computer(int code, Type type, String name, boolean status, boolean available, String os, int memory, int ram) {
        super(code, type, name, status, available);
        this.os = os;
        this.memory = memory;
        this.ram = ram;
    }

    @Override
    public void showAsset() {
        System.out.println("Code : " + this.getCode() + ", Name : " + this.getName() + ", OS : " + this.os + ", Memory : " + this.memory + ", RAM : " + this.ram);
    }




}
