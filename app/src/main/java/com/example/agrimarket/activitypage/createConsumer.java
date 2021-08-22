package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityCreateConsumerBinding;

import java.util.List;
import java.util.UUID;

import Controller.consumerController;
import View.ResultView;
import model.Consumer;
import model.Result;
import View.ConsumerView;

public class createConsumer extends AppCompatActivity implements createConsumerFragment.createConsumerListener, ResultView, ConsumerView {
    ActivityCreateConsumerBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateConsumerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void openDialog(View view) {
        createConsumerFragment consumerFragment = new createConsumerFragment();
        consumerFragment.show(getSupportFragmentManager(), "consumerFragment");
    }

    @Override //executes whenever user clicks 'Save' button at create Consumer Fragment
    public void setConsumerUser(String consumerUser, String consumerMobile, String consumerAddress, String consumerPassword) {
        String GUID = UUID.randomUUID().toString();
        consumerController controller = new consumerController(this, this, this);
        Consumer consumer = new Consumer(GUID, consumerMobile, consumerPassword, consumerUser, consumerAddress, 1);
        controller.createConsumer(consumer);
        //todo: start progress dialog on all button click of post and close it after response is fetched(VVI to stop burst post of data)


    }

    @Override
    public void responseReady(Result result) {
        String status = result.getStatus();

        Fragment consumerFragment = getSupportFragmentManager().findFragmentByTag("consumerFragment");
        if (consumerFragment != null && status.contentEquals("OK")) {
            DialogFragment dialogFragment = (DialogFragment) consumerFragment;
            dialogFragment.dismiss();
        }
        Toast.makeText(this, result.getResponse(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void comsumerReady(List<Consumer> consumers) { //data is ready in consumerview fetched from api

    }
}