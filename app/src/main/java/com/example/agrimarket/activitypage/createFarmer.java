package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import Adapters.farmerListAdapter;
import View.FarmerView;
import View.ResultView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityCreateFarmerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import Controller.farmerController;
import model.Farmer;
import model.Result;

public class createFarmer extends AppCompatActivity implements createFarmerFragment.createFarmerListener, ResultView, FarmerView {
    ActivityCreateFarmerBinding binding;
    farmerController farmerController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateFarmerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        farmerController = new farmerController(this, this, this);
        farmerController.getFarmer();


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
        createFarmerFragment.show(getSupportFragmentManager(), "FarmerFragment");
    }

    @Override
    public void farmeruserInsert(String farmerName, String farmerMobile, String farmerAddress, boolean farmerStatus) {

        /** perform farmer user update or save  action**/

        String GUID = UUID.randomUUID().toString();
        String defaultPassword = "123";
        String username = "admin";
        int status = (farmerStatus == true) ? 1 : 0;
        Farmer farmer = new Farmer();
        farmer.setGUID(GUID);
        farmer.setMobile(farmerMobile);
        farmer.setPassword(defaultPassword);
        farmer.setFullname(farmerName);
        farmer.setAddress(farmerAddress);
        farmer.setStatus(status);
        farmer.setUsername(username);
        farmerController.createFarmer(farmer);
    }

    @Override
    public void farmeruserUpdate(Integer ID, String farmerName, String farmerAddress, boolean farmerStatus) {
        int status = (farmerStatus == true) ? 1 : 0;
        Farmer farmer = new Farmer();
        farmer.setID(ID);
        farmer.setFullname(farmerName);
        farmer.setAddress(farmerAddress);
        farmer.setStatus(status);
        farmer.setUsername("admin");
    }

    @Override
    public void farmerReady(List<Farmer> farmers) {
        binding.rvFarmerList.setLayoutManager(new LinearLayoutManager(this));
        farmerListAdapter farmerListAdapter = new farmerListAdapter(farmers);
        binding.rvFarmerList.setAdapter(farmerListAdapter);
    }

    @Override
    public void responseReady(Result result) {
        String status = result.getStatus();

        Fragment farmerFragment = getSupportFragmentManager().findFragmentByTag("FarmerFragment");
        if (farmerFragment != null && status.contentEquals("OK")) {
            DialogFragment dialogFragment = (DialogFragment) farmerFragment;
            dialogFragment.dismiss();
        }
        Toast.makeText(this, result.getResponse(), Toast.LENGTH_SHORT).show();

    }
}