package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityCreateFarmerBinding;

public class createFarmer extends AppCompatActivity implements createFarmerFragment.createFarmerListener {
  ActivityCreateFarmerBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityCreateFarmerBinding.inflate(getLayoutInflater());
       View view=binding.getRoot();
       setContentView(view);


        /** Functions start **/
        binding.btnAddFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        createFarmerFragment createFarmerFragment = new createFarmerFragment();
        createFarmerFragment.show(getSupportFragmentManager(), "add farmer fragment");
    }

    @Override
    public void farmerUserAction(String farmerName, String farmerMobile, boolean farmerStatus) {

        /** perform farmer user update or save  action**/
    }
}