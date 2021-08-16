package Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import Interface.FarmerAPI;
import View.FarmerView;
import View.ResultView;
import model.Farmer;
import model.Product;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class farmerController {
    Context context;
    private FarmerView farmerView;
    private ResultView resultView;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.sheershakrg.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public farmerController(FarmerView farmerView, ResultView resultView, Context context) {
        this.context = context;
        this.farmerView = farmerView;
        this.resultView = resultView;
    }

    //function to post new farmer details to Retrofit interface
    public void createFarmer(Farmer farmer) {
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<Result> call = farmerAPI.postFarmer(farmer.getGUID(), farmer.getMobile(), farmer.getPassword(), farmer.getFullname(), farmer.getAddress(), farmer.getStatus(), farmer.getUsername());
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

    public void getFarmer() {
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<List<Farmer>> call = farmerAPI.getFarmer();
        call.enqueue(new Callback<List<Farmer>>() {
            @Override
            public void onResponse(Call<List<Farmer>> call, Response<List<Farmer>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Farmer> farmers = response.body();
                farmerView.farmerReady(farmers);
            }

            @Override
            public void onFailure(Call<List<Farmer>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}

