package com.example.agrimarket.activitypage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.agrimarket.databinding.FragmentCreateFarmerBinding;

import java.net.Inet4Address;

public class createFarmerFragment extends AppCompatDialogFragment {
    //defining viewBinding
    FragmentCreateFarmerBinding binding;
    private createFarmerListener farmerListener;
    public Integer ID;
    public static String OperationType;  // S-Save, U-update

    @NonNull

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentCreateFarmerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        OperationType = "S";
        builder.setView(view);

        //customize toggle button text and color

        binding.toggleFarmerUserStatus.setTextOff("निस्क्रिय");
        binding.toggleFarmerUserStatus.setTextOn("सक्रिय");
        binding.toggleFarmerUserStatus.setChecked(true);
        //set color change using selector and drawable xml..see stack overflow

        /*fetching bundle from recycler view adapter*/
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("ID")) {
            ID = bundle.getInt("ID");
            binding.etFarmerName.setText(bundle.getString("Name"));
            binding.etFarmerMobile.setText(bundle.getString("Mobile"));
            binding.etAddress.setText(bundle.getString("Address"));
            Boolean checked = bundle.getInt("Status") == 1 ? true : false;
            binding.toggleFarmerUserStatus.setChecked(checked);
            binding.etFarmerName.setEnabled(false);
            binding.etFarmerMobile.setEnabled(false);
            binding.etAddress.setEnabled(false);
            binding.btnSave.setText("अपडेट");
            OperationType = "U";

        }
        //button Save onclick listener
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = binding.etFarmerName.getText().toString();
                String txtMobile = binding.etFarmerMobile.getText().toString();
                String txtaddress = binding.etAddress.getText().toString();
                boolean isStatus = binding.toggleFarmerUserStatus.isChecked();

                if (validateInputs(txtName, txtMobile)) {
                    if (OperationType.contentEquals("S")) {
                        farmerListener.farmeruserInsert(txtName, txtMobile, txtaddress, isStatus);
                        dismiss();
                    } else if (OperationType.contentEquals("U")) {
                        farmerListener.farmeruserUpdate(ID, txtName, txtaddress, isStatus);
                    }

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
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            farmerListener = (createFarmerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface createFarmerListener {

        void farmeruserInsert(String farmerName, String farmerMobile, String farmerAddress, boolean farmerStatus);

        void farmeruserUpdate(Integer ID, String farmerName, String farmerAddress, boolean farmerStatus);
    }
}