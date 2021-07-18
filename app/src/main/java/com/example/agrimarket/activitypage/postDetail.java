package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.agrimarket.R;

public class postDetail extends AppCompatActivity {
    TextView ProductName, Unit, Quantity, Price, Stock, Location, Description, Homedelivery;
    Button Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        //typecasting
        ProductName = findViewById(R.id.tvProductName);
        Unit = findViewById(R.id.tvUnit);
        Quantity = findViewById(R.id.tvQuantity);
        Price = findViewById(R.id.tvPrice);
        Stock = findViewById(R.id.tvStockQuantity);
        Location = findViewById(R.id.tvLocation);
        Description = findViewById(R.id.tvDescription);
        Homedelivery = findViewById(R.id.tvHomeDelivery);
    }
}