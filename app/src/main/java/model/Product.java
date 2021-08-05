package model;

public class Product {

    private int ID;
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public float getMinRate() {
        return MinRate;
    }

    public float getMaxRate() {
        return MaxRate;
    }

    public int getUnit() {
        return Unit;
    }

    public int getStatus() {
        return Status;
    }

    private String Name;
    private float MinRate;


    public void setName(String name) {
        Name = name;
    }

    public void setMinRate(float minRate) {
        MinRate = minRate;
    }

    public void setMaxRate(float maxRate) {
        MaxRate = maxRate;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }

    public void setStatus(int status) {
        Status = status;
    }

    private float MaxRate;
    private int Unit;
    private int Status;
}
