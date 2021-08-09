package Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import Interface.FarmerAPI;
import View.ResultView;
import View.ConsumerView;
import model.Consumer;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class consumerController {
    Context context;
    ResultView resultView;
    ConsumerView consumerView;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.sheershakrg.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public consumerController(Context context, ResultView resultView, ConsumerView consumerView) {
        this.context = context;
        this.resultView = resultView;
        this.consumerView = consumerView;
    }

    private void createConsumer(Consumer consumer) {
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<Result> call = farmerAPI.postConsumer(consumer.getGUID(), consumer.getMobile(), consumer.getPassword(), consumer.getFullname(), consumer.getAddress(), consumer.getStatus());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                resultView.responseReady(result);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("Error",t.getMessage());
                Toast.makeText(context, "Technical Error..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
