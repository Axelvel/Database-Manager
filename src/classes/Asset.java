package classes;

import app.Type;


/**
 * Abstract class representing an asset
 * Each assets owns a code, composed of the acronym of its type and a number,
 * a type, a status and an availability
 */

public abstract class Asset {

    private String code;
    private Type type;
    private String status;
    private boolean availability;

    public Asset(String code, Type type, String status, boolean available) {
        this.code = code;
        this.type = type;
        this.status = status;
        this.availability = available;
    }
    public String getCode() { return this.code; }
    public Type getType() {return this.type;}
    public String getStatus() { return this.status; }

    public boolean isAvailable() { return this.availability; }

    public String toString(){
        return "ASSET " + this.getCode() + "\nType : "+this.getType()
                +"\nStatus :"+getStatus()+"\nAvailability :"+isAvailable();
    }

    public void setCode(String code) { this.code = code; }
    public void setType(Type type) { this.type = type; }
    public void setStatus(String status) { this.status = status; }
    public void setAvailability(boolean available) { this.availability = available; }


    /**VIRTUAL METHODS**/

    //Getters
    public String getBrand(){return null;}
    public String getOs(){return null;}
    public int getMemory(){return 0;}
    public int getRam(){return 0;}
    public String getSwitches(){return null;}
    public boolean getWireless(){return false;}

    //Setters
    public void setOs(String os){}
    public void setBrand(String brand){}
    public void setMemory(int memory){}
    public void setRam(int ram) {}
    public void setWireless(boolean w){}
    public void setSwitches(String s){}
}
