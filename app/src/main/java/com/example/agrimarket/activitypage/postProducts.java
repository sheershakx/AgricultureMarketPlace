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
import com.example.agrimarket.databinding.ActivityPostProductsBinding;

public class postProducts extends AppCompatActivity {
    ActivityPostProductsBinding binding;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostProductsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        /** Functions Start  **/

        //change switch text when toggled.
        binding.toggleHomeDelivery.setShowText(true);
        binding.toggleHomeDelivery.setTextOn("छ");
        binding.toggleHomeDelivery.setTextOff("छैन");

        //btnsave onclick function
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: get value for spinners, not sure what value to pick, maybe ll use hashmap to store text and value and fetch value only

                String txtquantity = binding.etQuantity.getText().toString();
                String txtprice = binding.etPrice.getText().toString();
                String txtstock = binding.etStockQuantity.getText().toString();
                String txtlocation = binding.etLocation.getText().toString();
                String txtdescription = binding.etDescription.getText().toString();
                if (validateInputs(txtquantity, txtprice, txtstock, txtlocation, txtdescription)) {


                }


            }
        });

    }

    public boolean validateInputs(String quantity, String price, String stock, String location, String description) {

        if (TextUtils.isEmpty(quantity)) {
            binding.etQuantity.requestFocus();
            binding.etQuantity.setError("परिमाण आवश्यक");

            return false;
        }
        if (TextUtils.isEmpty(price)) {
            binding.etPrice.requestFocus();
            binding.etPrice.setError("मुल्य आवश्यक");

            return false;

        }
        if (TextUtils.isEmpty(stock)) {
            binding.etStockQuantity.requestFocus();
            binding.etStockQuantity.setError("स्टक आवश्यक");

            return false;

        }
        if (TextUtils.isEmpty(location)) {
            binding.etLocation.requestFocus();
            binding.etLocation.setError("स्थान आवश्यक");
            return false;

        }
        if (TextUtils.isEmpty(description)) {
            binding.etDescription.requestFocus();
            binding.etDescription.setError("विवरण आवश्यक");

            return false;

        }
        return true;
    }
}