package com.example.agrimarket.activitypage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.agrimarket.R;


public class createProductFragment extends AppCompatDialogFragment {
    Spinner Unit;
    EditText ProductName,MinPrice,MaxPrice;
    Button Save;
  private  saveProductListener productListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_create_product, null);

        //typecasting
        Save=view.findViewById(R.id.btnProductSave);
        Unit=view.findViewById(R.id.spUnit);
        ProductName=view.findViewById(R.id.etProductName);
        MinPrice=view.findViewById(R.id.etMinPrice);
        MaxPrice=view.findViewById(R.id.etMaxPrice);
        builder.setView(view);

        /** Functions start **/
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtProductName=ProductName.getText().toString();
                String txtMinPrice=MinPrice.getText().toString();
                String txtMaxPrice=MaxPrice.getText().toString();
                if (validateInputs(txtProductName,txtMinPrice,txtMaxPrice))
                {
                    //save input using interface while functions is in the main activity page
                    productListener.onProductAction(txtProductName,txtMinPrice,txtMaxPrice);
                }
            }
        });

        return  builder.create();
    }

    //function for input validation
    public  boolean validateInputs(String txtProductName,String txtMinPrice,String txtMaxPrice)
    {
        boolean isvalid = true;
        if (TextUtils.isEmpty(txtProductName)) {
            ProductName.setError("बालिको नाम खालि छ");
            ProductName.requestFocus();
            isvalid = false;
            return isvalid;
        }
        if (TextUtils.isEmpty(txtMinPrice)) {
            MinPrice.setError("न्युनतम मुल्य खालि छ");
            MinPrice.requestFocus();
            isvalid = false;
            return isvalid;
        }
        if (TextUtils.isEmpty(txtMaxPrice)) {
            MaxPrice.setError("अधिकतम मुल्य खालि छ");
            MaxPrice.requestFocus();
            isvalid = false;
            return isvalid;
        }

        return  isvalid;

    }
    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        try {
            productListener = (saveProductListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    public interface saveProductListener
    {
        void onProductAction(String txtProductName,String txtMinPrice,String txtMaxPrice);

    }
}