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

public class createFarmerFragment extends AppCompatDialogFragment {
    Button Save;
    ToggleButton UserStatus;
    EditText FarmerName, FarmerMobile;
    private createFarmerListener farmerListener;

    @NonNull

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_create_farmer, null);
        builder.setView(view);
        
        //typecasting
        Save = view.findViewById(R.id.btnSave);
        UserStatus = view.findViewById(R.id.toggleFarmerUserStatus);
        FarmerName = view.findViewById(R.id.etFarmerName);
        FarmerMobile = view.findViewById(R.id.etFarmerMobile);
        //customize toggle button text and color
        UserStatus.setTextOff("निस्क्रिय");
        UserStatus.setTextOn("सक्रिय");
        UserStatus.setChecked(true);
        //set color change using selector and drawable xml..see stack overflow

        //button Save onclick listener
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName=FarmerName.getText().toString();
                String txtMobile=FarmerMobile.getText().toString();
                boolean isStatus=UserStatus.isChecked();
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
            FarmerName.setError("कृषकको नाम खालि छ");
            FarmerName.requestFocus();
            isvalid = false;
            return isvalid;

        }
        if (TextUtils.isEmpty(txtmobile)) {
            FarmerMobile.setError("कृषकको मोबाइल नं खालि छ");
            FarmerMobile.requestFocus();
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