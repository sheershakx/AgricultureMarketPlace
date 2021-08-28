package Interface;

import java.util.List;

import model.Farmer;
import model.OrderList;
import model.Posts;
import model.Product;
import model.Result;
import model.Unit;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;

public interface FarmerAPI {

    @GET("getProduct.php")
    Call<List<Product>> getProduct();

    @GET("getUnit.php")
    Call<List<Unit>> getUnit();

    @GET("getFarmer.php")
    Call<List<Farmer>> getFarmer();

    @GET("getPost.php")
    Call<List<Posts>> getPost();

    @FormUrlEncoded
    @POST("getOrderList.php")
    Call<List<OrderList>> getOrderList(@Field("ID") Integer FarmerID
            , @Field("UserType") String UserType);


    @FormUrlEncoded
    @POST("createProduct.php")
    Call<Result> postProduct(
            @Field("Name") String Name,
            @Field("Unit") Integer unit,
            @Field("MinRate") float minrate,
            @Field("MaxRate") float maxrate,
            @Field("Status") int status,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("updateProduct.php")
    Call<Result> updateProduct(
            @Field("ID") Integer ID,
            @Field("Name") String Name,
            @Field("Unit") Integer unit,
            @Field("MinRate") float minrate,
            @Field("MaxRate") float maxrate,
            @Field("Status") int status,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("createFarmer.php")
    Call<Result> postFarmer(
            @Field("GUID") String GUID,
            @Field("Mobile") String Mobile,
            @Field("Password") String Password,
            @Field("Fullname") String Fullname,
            @Field("Address") String Address,
            @Field("Status") int Status,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("updateFarmer.php")
    Call<Result> updateFarmer(

            @Field("ID") Integer ID,
            @Field("Fullname") String Fullname,
            @Field("Address") String Address,
            @Field("Status") int Status,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("createConsumer.php")
    Call<Result> postConsumer(
            @Field("GUID") String GUID,
            @Field("Mobile") String Mobile,
            @Field("Password") String Password,
            @Field("Fullname") String Fullname,
            @Field("Address") String Address,
            @Field("Status") int Status

    );

    @FormUrlEncoded
    @POST("createOrder.php")
    Call<Result> postOrders(
            @Field("PostID") int PostID,
            @Field("ConsumerID") int ConsumerID,
            @Field("FarmerID") int FarmerID,
            @Field("OrderDate") String OrderDate,
            @Field("Quantity") float Quantity,
            @Field("Homedelivery") int Homedelivery,
            @Field("Address") String Address,
            @Field("Mobile") String Mobile,
            @Field("Status") String Status


    );

    @FormUrlEncoded
    @POST("createPost.php")
        //create a new post for sell by farmer
    Call<Result> postFeed(
            @Field("FarmerID") int FarmerID,
            @Field("DateNep") String DateNep,
            @Field("Product") int Product,
            @Field("Unit") int Unit,
            @Field("Quantity") float Quantity,
            @Field("Price") float Price,
            @Field("Stock") float Stock,
            @Field("Location") String Location,
            @Field("Description") String Description,
            @Field("HomeDelivery") int HomeDelivery,
            @Field("Status") String Status
    );


}
