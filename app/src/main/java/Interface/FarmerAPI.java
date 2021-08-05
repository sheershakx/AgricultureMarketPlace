package Interface;

import java.util.List;

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



//    @POST("createProduct.php")
//    Call <Product> postProduct(
//            @Query("Name") String Name,
//            @Query("Unit") Integer Unit,
//            @Query("MinRate") float MinRate,
//            @Query("MaxRate") float MaxRate,
//            @Query("Status") Integer Status,
//            @Query("username") String username
//
//    );
    @FormUrlEncoded
    @POST("createProduct.php")
    Call<Result> postProduct(
            @Field("Name") String Name,
            @Field("Unit") Integer unit,
            @Field("MinRate") float minrate,
            @Field("MaxRate") float maxrate,
            @Field("Status") Integer status,
            @Field("username") String username
    );


}
