package Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import Interface.FarmerAPI;
import View.ResultView;
import model.Posts;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class postController {
    ResultView resultView;
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.sheershakrg.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public postController(ResultView resultView, Context context) {
        this.resultView = resultView;
        this.context = context;
    }

    //functon to create post
    public void createPost(Posts posts) {
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<Result> call = farmerAPI.postFeed(posts.getFarmerID(), posts.getDateNep(), posts.getProduct(), posts.getUnit(), posts.getQuantity(), posts.getPrice(), posts.getStock(), posts.getLocation(), posts.getDescription(), posts.getHomeDelivery(), posts.getStatus());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error:", String.valueOf(response.code()));
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                //if successful then pass the response json to Result Interface via Rsult model
                Result result = response.body();
                resultView.responseReady(result);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("Error:", t.getMessage());

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
