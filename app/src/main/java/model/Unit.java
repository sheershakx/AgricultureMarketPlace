package model;

public class Unit {
    private int ID;
    private String Unit;

    public void setUnit(String unit) {
        Unit = unit;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getID() {
        return ID;
    }

    public String getUnit() {
        return Unit;
    }

    public int getStatus() {
        return Status;
    }

    private int Status;
}
