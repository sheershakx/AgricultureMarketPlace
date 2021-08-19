package model;

public class Farmer {
    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;
    private String GUID;
    private String Mobile;

    public void setGUID(String GUID) {
        this.GUID = GUID;
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

    public void setStatus(int status) {
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

    public int getStatus() {
        return Status;
    }

    public String getUsername() {
        return username;
    }

    private String Password;
    private String Fullname;
    private String Address;
    private int Status;
    private String username;
}
