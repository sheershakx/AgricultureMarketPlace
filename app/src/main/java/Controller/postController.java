package Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import Interface.FarmerAPI;
import View.FeedView;
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
    FeedView feedView;
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.sheershakrg.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public postController(ResultView resultView, FeedView feedView, Context context) {
        this.resultView = resultView;
        this.feedView = feedView;
        this.context = context;
    }

    public void getPost() {

        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<List<Posts>> call = farmerAPI.getPost();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error code:" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Posts> posts = response.body();
                feedView.feedReady(posts);

            }


            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
