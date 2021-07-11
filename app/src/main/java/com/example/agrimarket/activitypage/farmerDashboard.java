package com.example.agrimarket.activitypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.google.android.material.navigation.NavigationView;

public class farmerDashboard extends AppCompatActivity {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_dashboard);
        //typecasting
        navigationView = findViewById(R.id.fNavigationView);
        toolbar = findViewById(R.id.ftoolbar);
        drawerLayout = findViewById(R.id.fdrawerLayout);
        /** Functions start **/

       //tollbar and nav activities
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
       drawerLayout.addDrawerListener(actionBarDrawerToggle);
       actionBarDrawerToggle.syncState();
       //navigation on click events
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId())
               {
                   case R.id.postitem:
                       startActivity(new Intent(getApplicationContext(),postProducts.class));
                       break;
               }
               return  false;
            }
        });

    }
}