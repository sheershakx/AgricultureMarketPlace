package model;

public class OrderList {
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

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

    public String getConsumername() {
        return Consumername;
    }

    public void setConsumername(String consumername) {
        Consumername = consumername;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    private int OrderID ;
    private int PostID ;
    private int ConsumerID ;
    private String OrderDate ;
    private String Consumername ;
    private String Productname ;
    private String Status ;
}
