package Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import Interface.FarmerAPI;
import View.ProductView;
import View.ResultView;
import model.Product;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class productController {
    Context context;
    private ProductView productView;
    private ResultView resultView;
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.sheershakrg.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public productController(ProductView view,ResultView resultView, Context context) {
        this.productView = view;
        this.resultView=resultView;
        this.context = context;
    }


    public void getProduct() {

        //creating return list
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<List<Product>> call = farmerAPI.getProduct();
        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Product> products = response.body();
                productView.productReady(products);


            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void postProduct(Product product) {
        //creating return list
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);

       Call<Result> call = farmerAPI.postProduct(product.getName(),product.getUnit(),product.getMinRate(),product.getMaxRate(),product.getStatus(),"admin");
        call.enqueue(new Callback<Result>() {

            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Result productresponse=response.body();
                resultView.responseReady(productresponse);



            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("retrofit",t.getMessage());
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void updateProduct(Product product) {
        //creating return list
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);

        Call<Result> call = farmerAPI.updateProduct(product.getID(),product.getName(),product.getUnit(),product.getMinRate(),product.getMaxRate(),product.getStatus(),"admin");
        call.enqueue(new Callback<Result>() {

            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Result productresponse=response.body();
                resultView.responseReady(productresponse);



            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("retrofit",t.getMessage());
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }


}


