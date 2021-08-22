package model;

public class Posts {
    private int PostID;
    private int FarmerID;
    private String DateNep;



    public void setPostID(int postID) {
        PostID = postID;
    }

    public void setFarmername(String farmername) {
        Farmername = farmername;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public int getPostID() {
        return PostID;
    }

    private String Farmername;


    private String Productname;
    private String UnitName;

    public String getFarmername() {
        return Farmername;
    }

    public String getProductname() {
        return Productname;
    }

    public String getUnitName() {
        return UnitName;
    }

    public int getFarmerID() {
        return FarmerID;
    }

    public String getDateNep() {
        return DateNep;
    }

    public int getProduct() {
        return Product;
    }

    public int getUnit() {
        return Unit;
    }

    public float getQuantity() {
        return Quantity;
    }

    public float getPrice() {
        return Price;
    }

    public float getStock() {
        return Stock;
    }

    public String getLocation() {
        return Location;
    }

    public String getDescription() {
        return Description;
    }

    public int getHomeDelivery() {
        return HomeDelivery;
    }

    public String getStatus() {
        return Status;
    }

    private int Product;

    public Posts(int farmerID, String dateNep, int product, int unit, float quantity, float price, float stock, String location, String description, int homeDelivery, String status) {
        FarmerID = farmerID;
        DateNep = dateNep;
        Product = product;
        Unit = unit;
        Quantity = quantity;
        Price = price;
        Stock = stock;
        Location = location;
        Description = description;
        HomeDelivery = homeDelivery;
        Status = status;
    }

    private int Unit;

    public void setFarmerID(int farmerID) {
        FarmerID = farmerID;
    }

    public void setDateNep(String dateNep) {
        DateNep = dateNep;
    }

    public void setProduct(int product) {
        Product = product;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void setStock(float stock) {
        Stock = stock;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setHomeDelivery(int homeDelivery) {
        HomeDelivery = homeDelivery;
    }

    public void setStatus(String status) {
        Status = status;
    }

    private float Quantity;
    private float Price;
    private float Stock;
    private String Location;
    private String Description;
    private int HomeDelivery;
    private String Status;
}
