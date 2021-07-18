package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agrimarket.R;

public class createProduct extends AppCompatActivity implements createProductFragment.saveProductListener {
    RecyclerView recyclerView;
    Button addProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        //TYpecasting
        addProduct = findViewById(R.id.btnAddProduct);

        /** functions start **/
        addProduct.setOnClickListener(new View.OnClickListener() {
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