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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.FragmentCreateProductBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import View.UnitView;
import model.Unit;
import Controller.unitController;

public class createProductFragment extends AppCompatDialogFragment implements UnitView {

    private saveProductListener productListener;
    private FragmentCreateProductBinding binding;
    HashMap<String, Integer> unitHash = new HashMap<String, Integer>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentCreateProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        builder.setView(view);

        //calling Unit Contoller
        unitController unitController = new unitController(this, getContext());
        unitController.getUnit();

        //setting up Unit spinner


        /** Functions start **/
        binding.btnProductSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtProductName = binding.etProductName.getText().toString();
                String txtMinPrice = binding.etMinPrice.getText().toString();
                String txtMaxPrice = binding.etMaxPrice.getText().toString();
                Integer txtUnitID=unitHash.get(binding.spUnit.getSelectedItem().toString());
                if (validateInputs(txtProductName, txtUnitID,txtMinPrice, txtMaxPrice)) {
                    //save input using interface while functions is in the main activity page
                    productListener.onProductAction(txtProductName,txtUnitID, txtMinPrice, txtMaxPrice);
                }
            }
        });

        return builder.create();
    }

    //function for input validation
    public boolean validateInputs(String txtProductName, Integer txtUnitID,String txtMinPrice, String txtMaxPrice) {
        boolean isvalid = true;
        if (TextUtils.isEmpty(txtProductName)) {
            binding.etProductName.setError("बालिको नाम खालि छ");
            binding.etProductName.requestFocus();
            isvalid = false;
            return isvalid;
        }
        if (TextUtils.isEmpty(txtMinPrice)) {
            binding.etMinPrice.setError("न्युनतम मुल्य खालि छ");
            binding.etMinPrice.requestFocus();
            isvalid = false;
            return isvalid;
        }
        if (TextUtils.isEmpty(txtMaxPrice)) {
            binding.etMaxPrice.setError("अधिकतम मुल्य खालि छ");
            binding.etMaxPrice.requestFocus();
            isvalid = false;
            return isvalid;
        }
        if (txtUnitID.equals(0)) {
            Toast.makeText(getContext(), "इकाई छान्नुहोस् !!", Toast.LENGTH_SHORT).show();
            binding.spUnit.requestFocus();
            isvalid = false;
            return isvalid;
        }

        return isvalid;

    }

    //function for spinner data set
    public void setUnitSpinner(HashMap<String, Integer> hashMap) {

        Collection<String> collection = hashMap.keySet();
        String[] uniArray = collection.toArray(new String[hashMap.size()]);
        ArrayAdapter<String> unitadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, uniArray);
        unitadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spUnit.setAdapter(unitadapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            productListener = (saveProductListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    @Override
    public void UnitReady(List<Unit> units) {

        for (Unit unit : units) {
            unitHash.put(unit.getUnit(), unit.getID());
            // Toast.makeText(getContext(), unitHash.size(), Toast.LENGTH_SHORT).show(); //use ""+ in toast for error solve

        }
        setUnitSpinner(unitHash);

    }

    public interface saveProductListener {
        void onProductAction(String txtProductName,Integer txtUnitID ,String txtMinPrice, String txtMaxPrice);

    }
}