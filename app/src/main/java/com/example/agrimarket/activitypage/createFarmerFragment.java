package com.example.agrimarket.activitypage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.FragmentCreateFarmerBinding;

public class createFarmerFragment extends AppCompatDialogFragment {
    //defining viewBinding
    FragmentCreateFarmerBinding binding;
    private createFarmerListener farmerListener;

    @NonNull

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding=FragmentCreateFarmerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        builder.setView(view);
        
        //typecasting

        //customize toggle button text and color

        binding.toggleFarmerUserStatus.setTextOff("निस्क्रिय");
        binding.toggleFarmerUserStatus.setTextOn("सक्रिय");
        binding.toggleFarmerUserStatus.setChecked(true);
        //set color change using selector and drawable xml..see stack overflow

        //button Save onclick listener
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName=binding.etFarmerName.getText().toString();
                String txtMobile=binding.etFarmerMobile.getText().toString();
                boolean isStatus=binding.toggleFarmerUserStatus.isChecked();
                if(validateInputs(txtName,txtMobile))
                {
                    farmerListener.farmerUserAction(txtName,txtMobile,isStatus);
                    dismiss();
                }
            }
        });
        return builder.create();
    }


    private boolean validateInputs(String txtname, String txtmobile) {
        boolean isvalid = true;
        if (TextUtils.isEmpty(txtname)) {
            binding.etFarmerName.setError("कृषकको नाम खालि छ");
            binding.etFarmerName.requestFocus();
            isvalid = false;
            return isvalid;

        }
        if (TextUtils.isEmpty(txtmobile)) {
            binding.etFarmerMobile.setError("कृषकको मोबाइल नं खालि छ");
            binding.etFarmerMobile.requestFocus();
            isvalid = false;
            return isvalid;

        }
        return isvalid;


    }

    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        try {
            farmerListener = (createFarmerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface createFarmerListener
    {
        void farmerUserAction(String farmerName, String farmerMobile, boolean farmerStatus);
    }
}