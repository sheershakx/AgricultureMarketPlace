package Interface;

import java.util.List;

import model.Farmer;
import model.Product;
import model.Result;
import model.Unit;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;

public interface FarmerAPI {

    @GET("getProduct.php")
    Call<List<Product>> getProduct();

    @GET("getUnit.php")
    Call<List<Unit>> getUnit();

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


}
