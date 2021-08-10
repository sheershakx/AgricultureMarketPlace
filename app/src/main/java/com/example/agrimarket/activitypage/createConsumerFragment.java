package com.example.agrimarket.activitypage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.FragmentCreateConsumerBinding;


public class createConsumerFragment extends AppCompatDialogFragment {
    FragmentCreateConsumerBinding binding;
    private createConsumerListener consumerListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            consumerListener = (createConsumerFragment.createConsumerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentCreateConsumerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        builder.setView(view);


        /**FUnctions starts**/
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = binding.consumerName.getText().toString();
                String txtMobile = binding.comsumerMobile.getText().toString();
                String txtaddress = binding.comsumerAddress.getText().toString();
                String txtpassword = binding.consumerPassword.getText().toString();
                String txtRepassword = binding.consumerRepassword.getText().toString();
                if (validateInputs(txtName, txtMobile, txtaddress,txtpassword,txtRepassword)) {
                    consumerListener.setConsumerUser(txtName, txtMobile, txtaddress,txtpassword);

                }
            }
        });
        return builder.create();  //place this line at last of oncreateDialog
    }

    //function to validate input fields
    private boolean validateInputs(String txtname, String txtmobile, String txtaddress,String txtpassword,String txtRepassword) {
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
        if (txtmobile.length()!=10) {
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

    //interface to execute function at Consumer Acitvity on 'save' on click at consumer fragment
    public interface createConsumerListener {
        void setConsumerUser(String consumerUser, String consumerMobile, String consumerAddress,String consumerPassword);
    }
}