package com.example.agrimarket.activitypage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.agrimarket.R;

public class postProducts extends AppCompatActivity {
    public EditText Quantity, Stock, Location, Description, Price;
    Button Save;
    Spinner Productname, Unit;

    Switch Homedelivery;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_products);

        //typecasting
        Save = findViewById(R.id.btnSave);
        Productname = findViewById(R.id.spProductName);
        Unit = findViewById(R.id.spUnit);
        Price = findViewById(R.id.etPrice);
        Homedelivery = findViewById(R.id.toggleHomeDelivery);
        Quantity = findViewById(R.id.etQuantity);
        Stock = findViewById(R.id.etStockQuantity);
        Location = findViewById(R.id.etLocation);
        Description = findViewById(R.id.etDescription);
        /** Functions Start  **/

        //change switch text when toggled.
        Homedelivery.setShowText(true);
        Homedelivery.setTextOn("छ");
        Homedelivery.setTextOff("छैन");

        //btnsave onclick function
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: get value for spinners, not sure what value to pick, maybe ll use hashmap to store text and value and fetch value only
                String txtquantity = Quantity.getText().toString();
                String txtprice = Price.getText().toString();
                String txtstock = Stock.getText().toString();
                String txtlocation = Location.getText().toString();
                String txtdescription = Description.getText().toString();
                if (validateInputs(txtquantity, txtprice, txtstock, txtlocation, txtdescription)) {
                    // perform save logic here
                }


            }
        });

    }

    public boolean validateInputs(String quantity, String price, String stock, String location, String description) {
        boolean isvalid = true;
        if (TextUtils.isEmpty(quantity)) {
            Quantity.requestFocus();
            Quantity.setError("परिमाण आवश्यक");
            isvalid = false;
            return isvalid;
        }
        if (TextUtils.isEmpty(price)) {
            Price.requestFocus();
            Price.setError("मुल्य आवश्यक");
            isvalid = false;
            return isvalid;

        }
        if (TextUtils.isEmpty(stock)) {
            Stock.requestFocus();
            Stock.setError("स्टक आवश्यक");
            isvalid = false;
            return isvalid;

        }
        if (TextUtils.isEmpty(location)) {
            Location.requestFocus();
            Location.setError("स्थान आवश्यक");
            isvalid = false;
            return isvalid;

        }
        if (TextUtils.isEmpty(description)) {
            Description.requestFocus();
            Description.setError("विवरण आवश्यक");
            isvalid = false;
            return isvalid;

        }
        return isvalid;
    }
}