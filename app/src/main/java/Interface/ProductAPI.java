package Interface;

import java.util.List;

import model.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPI {

    @GET("getProduct.php")
    Call<List<Product>> getProduct();
}
