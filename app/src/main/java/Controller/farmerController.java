package Controller;

import android.content.Context;

import Interface.FarmerAPI;
import View.FarmerView;
import View.ResultView;
import model.Farmer;
import retrofit2.Call;
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
       // Call<Farmer> call=farmerAPI()
        }

    }
}

