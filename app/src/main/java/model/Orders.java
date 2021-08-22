package model;

public class Orders {
    private int ID;
    private int Homedelivery;
    private String Status;

    public int getConsumerID() {
        return ConsumerID;
    }

    public void setConsumerID(int consumerID) {
        ConsumerID = consumerID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    private int OrderID;
    private int ConsumerID;
    private String OrderDate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHomedelivery() {
        return Homedelivery;
    }

    public void setHomedelivery(int homedelivery) {
        Homedelivery = homedelivery;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getFarmerID() {
        return FarmerID;
    }

    public void setFarmerID(int farmerID) {
        FarmerID = farmerID;
    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    private int FarmerID;
    private int PostID;
    private float Quantity;
    private String Address;
    private String Mobile;

    public float getQuantity() {
        return Quantity;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
