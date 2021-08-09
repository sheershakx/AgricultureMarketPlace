package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityCreateConsumerBinding;

public class createConsumer extends AppCompatActivity {
    ActivityCreateConsumerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateConsumerBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
    }

    public void openDialog(View view) {
        createConsumerFragment consumerFragment=new createConsumerFragment();
        consumerFragment.show(getSupportFragmentManager(),"consumerFragment");
    }
}