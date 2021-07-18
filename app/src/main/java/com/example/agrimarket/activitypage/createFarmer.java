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

public class createFarmer extends AppCompatActivity implements createFarmerFragment.createFarmerListener {
    Button Addfarmer;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_farmer);
        //typecasting
        Addfarmer = findViewById(R.id.btnAddFarmer);
        textView=findViewById(R.id.txtSampleView);

        /** Functions start **/
        Addfarmer.setOnClickListener(new View.OnClickListener() {
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
        textView.setText(farmerName+","+farmerMobile+","+farmerStatus);
        /** perform farmer user update or save  action**/
    }
}