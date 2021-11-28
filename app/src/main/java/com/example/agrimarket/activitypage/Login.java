package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityLoginBinding;
import com.google.android.material.button.MaterialButton;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        /** Page Functions Start **/
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = binding.etUsername.getText().toString();
                String txtPassword = binding.etPassword.getText().toString();
                if (txtUsername.isEmpty()) {
                    binding.etUsername.setError("Username required");
                    binding.etUsername.requestFocus();
                    binding.etUsername.setFocusable(true);
                    return;
                }
                if (txtPassword.isEmpty()) {
                    binding.etPassword.setError("Password required");
                    binding.etPassword.requestFocus();
                    binding.etPassword.setFocusable(true);
                    return;
                }
                //use username and passcode verify logic

                if (txtUsername.equals("consumer") && txtPassword.equals("123")) {
                    startActivity(new Intent(getApplicationContext(),consumerDashboard.class));
                    return;
                }
                else  if (txtUsername.equals("farmer") && txtPassword.equals("123")) {
                    startActivity(new Intent(getApplicationContext(),farmerDashboard.class));
                    return;
                }
               else if (txtUsername.equals("admin") && txtPassword.equals("123")) {
                    startActivity(new Intent(getApplicationContext(),createFarmer.class));
                    return;
                }
                else Toast.makeText(Login.this, "Invalid Login.", Toast.LENGTH_SHORT).show();


            }
        });
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), farmerDashboard.class));
            }
        });

    }
}