package Controller;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import Interface.FarmerAPI;
import View.ProductView;
import View.UnitView;
import model.Product;
import model.Unit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class unitController {
    Context context;
    private UnitView unitView;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.sheershakrg.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public unitController(UnitView view, Context context) {
        this.unitView = view;
        this.context = context;
    }


    public void getUnit() {

        //creating return list
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<List<Unit>> call = farmerAPI.getUnit();
        call.enqueue(new Callback<List<Unit>>() {

            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Unit> units = response.body();

                unitView.UnitReady(units);


            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }



}
