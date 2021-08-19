package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.google.android.material.button.MaterialButton;

public class Login extends AppCompatActivity {
    EditText username, password;
    MaterialButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //typecasting of widgets
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        /** Page Functions Start **/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = username.getText().toString();
                String txtPassword = password.getText().toString();
                if (txtUsername.isEmpty()) {
                    username.setError("Username required");
                    username.requestFocus();
                    username.setFocusable(true);
                    return;
                }
                if (txtPassword.isEmpty()) {
                    password.setError("Password required");
                    password.requestFocus();
                    password.setFocusable(true);
                    return;
                }
                //use username and passcode verify logic

                if (txtUsername.equals("admin") && txtPassword.equals("123")) {
                    Log.d("response", "Loggedin successfully");
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    //  startActivity(new Intent(getApplicationContext(), farmerDashboard.class));
                    // startActivity(new Intent(getApplicationContext(), consumerDashboard.class));
                    startActivity(new Intent(getApplicationContext(), createFarmer.class));
                } else Toast.makeText(Login.this, "Invalid Login.", Toast.LENGTH_SHORT).show();


            }
        });

    }
}