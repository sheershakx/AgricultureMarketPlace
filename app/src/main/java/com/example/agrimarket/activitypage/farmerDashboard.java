package com.example.agrimarket.activitypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityFarmerDashboardBinding;
import com.google.android.material.navigation.NavigationView;

public class farmerDashboard extends AppCompatActivity {
    ActivityFarmerDashboardBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFarmerDashboardBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        /** Functions start **/

        //tollbar and nav activities
        setSupportActionBar(binding.ftoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.fNavigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.fdrawerLayout, binding.ftoolbar, R.string.app_name, R.string.app_name);
        binding.fdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //navigation on click events
        binding.fNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.postitem:
                        startActivity(new Intent(getApplicationContext(), postProducts.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), farmerProfile.class));
                        break;
                    case R.id.activitylog:
                        startActivity(new Intent(getApplicationContext(), createFarmer.class));
                        break;
                }
                return false;
            }
        });

    }
}