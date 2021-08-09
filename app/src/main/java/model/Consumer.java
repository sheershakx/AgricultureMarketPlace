package model;

public class Consumer {
    private int ID;
    private String GUID;
    private String Mobile;
    private String Password;

    public Consumer(String GUID, String mobile, String password, String fullname, String address, int status) {
        this.GUID = GUID;
        Mobile = mobile;
        Password = password;
        Fullname = fullname;
        Address = address;
        Status = status;
    }

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

    private String Fullname;
    private String Address;
    private int Status;
}
