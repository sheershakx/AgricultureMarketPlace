package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
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

public class createConsumer extends AppCompatActivity implements ResultView, ConsumerView {
    ActivityCreateConsumerBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateConsumerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        consumerController controller = new consumerController(this, this, this);

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = binding.consumerName.getText().toString();
                String txtMobile = binding.comsumerMobile.getText().toString();
                String txtaddress = binding.comsumerAddress.getText().toString();
                String txtpassword = binding.consumerPassword.getText().toString();
                String txtRepassword = binding.consumerRepassword.getText().toString();
                if (validateInputs(txtName, txtMobile, txtaddress, txtpassword, txtRepassword)) {
                    String GUID = UUID.randomUUID().toString();

                    Consumer consumer = new Consumer(GUID, txtMobile, txtpassword, txtName, txtaddress, 1);
                    controller.createConsumer(consumer);

                }
            }
        });
    }

    //function to validate input fields
    private boolean validateInputs(String txtname, String txtmobile, String txtaddress, String txtpassword, String txtRepassword) {
        if (TextUtils.isEmpty(txtname)) {
            binding.consumerName.setError("नाम खालि छ");
            binding.consumerName.requestFocus();

            return false;

        }
        if (TextUtils.isEmpty(txtmobile)) {
            binding.comsumerMobile.setError("मोबाइल नं खालि छ");
            binding.comsumerMobile.requestFocus();

            return false;

        }
        if (txtmobile.length() != 10) {
            binding.comsumerMobile.setError("मोबाइल नं १० अंकको हुनुपर्दछ");
            binding.comsumerMobile.requestFocus();

            return false;

        }
        if (TextUtils.isEmpty(txtaddress)) {
            binding.comsumerAddress.setError("ठेगाना खालि छ");
            binding.comsumerAddress.requestFocus();

            return false;

        }
        if (TextUtils.isEmpty(txtpassword)) {
            binding.consumerPassword.setError("पासवर्ड खालि छ");
            binding.consumerPassword.requestFocus();

            return false;

        }
        if (TextUtils.isEmpty(txtRepassword)) {
            binding.consumerRepassword.setError("पुन:पासवर्ड खालि छ");
            binding.consumerRepassword.requestFocus();

            return false;

        }
        if (!txtpassword.contentEquals(txtRepassword)) {
            binding.consumerRepassword.setError("पासवर्ड र पुन पासवर्ड मिलेन");
            binding.consumerRepassword.requestFocus();

            return false;

        }
        return true;


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