package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityCreateProductBinding;

import java.util.ArrayList;
import java.util.List;

import Interface.ProductAPI;
import model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class createProduct extends AppCompatActivity implements createProductFragment.saveProductListener {
    ActivityCreateProductBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        /*RETROFIT OPERATIONS*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.sheershakrg.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductAPI productAPI = retrofit.create(ProductAPI.class);
        Call<List<Product>> call = productAPI.getProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(createProduct.this, "Error Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Product> products = response.body();
                for (Product product : products) {
                    String collection = "";
                    collection += "नाम: " + product.getName() + "\n";
                    collection += "मिन: " + product.getMinRate() + "\n";
                    collection += "माक्ष्: " + product.getMaxRate() + "\n\n";

                    binding.TextView.append(collection);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(createProduct.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        /** functions start **/
        binding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProductFragment productFragment = new createProductFragment();
                productFragment.show(getSupportFragmentManager(), "Product Fragment");
            }
        });
    }

    @Override
    public void onProductAction(String txtProductName, String txtMinPrice, String txtMaxPrice) {
        Toast.makeText(this, txtProductName, Toast.LENGTH_SHORT).show();
    }
}