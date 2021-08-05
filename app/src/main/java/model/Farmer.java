package model;

public class Farmer {
    private int ID;
    private String GUID;
    private String Mobile;

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public Farmer(String GUID, String mobile, String password, String fullname, String address, String status, String username) {
        this.GUID = GUID;
        Mobile = mobile;
        Password = password;
        Fullname = fullname;
        Address = address;
        Status = status;
        this.username = username;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public String getGUID() {
        return GUID;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPassword() {
        return Password;
    }

    public String getFullname() {
        return Fullname;
    }

    public String getAddress() {
        return Address;
    }

    public String getStatus() {
        return Status;
    }

    public String getUsername() {
        return username;
    }

    private String Password;
    private String Fullname;
    private String Address;
    private String Status;
    private String username;
}
