package com.example.agrimarket.activitypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class consumerDashboard extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_dashboard);

        //typecasting
        frameLayout = findViewById(R.id.frameConsumer);
        bottomNavigationView = findViewById(R.id.buttonnavConsumer);

        /** FragmentManager creation for start **/
        //add over replace prerafable for first fragment commit
        //DONOT DELETE  // getSupportFragmentManager().beginTransaction().replace(R.id.frameConsumer, new consumerFeedFragment(), "defaultFragment").commit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameConsumer, new consumerFeedFragment(), "defaultFragment").commit();


        /** Functions start **/
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                androidx.fragment.app.Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.menuconsumerfeed:
                        selectedFragment = new consumerFeedFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameConsumer, selectedFragment, "aa").commit();
                        break;
                    case R.id.menuconsumerorder:
                        Toast.makeText(consumerDashboard.this, "Order", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuconsumerlog:
                        Toast.makeText(consumerDashboard.this, "Log", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}