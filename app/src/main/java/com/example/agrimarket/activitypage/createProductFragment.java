package com.example.agrimarket.activitypage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.agrimarket.databinding.FragmentCreateProductBinding;

import java.util.Arrays;
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
    private Integer ID, Unit;
    private String ProductName;
    private float MinRate, MaxRate;
    public static String OperationType;   //S -save , U-Update

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentCreateProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        OperationType = "S";

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
                Integer txtUnitID = unitHash.get(binding.spUnit.getSelectedItem().toString());
                if (validateInputs(txtProductName, txtUnitID, txtMinPrice, txtMaxPrice)) {
                    //save input using interface while functions is in the main activity page
                    if (OperationType.contentEquals("S")) {
                        productListener.onProductSave(txtProductName, txtUnitID, txtMinPrice, txtMaxPrice);
                    } else if (OperationType.contentEquals("U")) {
                        productListener.onProductUpdate(ID, txtProductName, txtUnitID, txtMinPrice, txtMaxPrice);

                    }
                }
            }
        });

        return builder.create();
    }

    //function for input validation
    public boolean validateInputs(String txtProductName, Integer txtUnitID, String txtMinPrice, String txtMaxPrice) {
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
    public void setUnitSpinner(HashMap<String, Integer> hashMap, Integer UnitID) {

        Collection<String> collection = hashMap.keySet();
        Collection<Integer> valuecollection = hashMap.values();
        String[] uniArray = collection.toArray(new String[hashMap.size()]);
        Integer[] valueArray = valuecollection.toArray(new Integer[hashMap.size()]);
        ArrayAdapter<String> unitadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, uniArray);
        unitadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spUnit.setAdapter(unitadapter);
        if (UnitID != null && UnitID > 0) {
            Integer pos = Arrays.asList(valueArray).indexOf(UnitID);
            binding.spUnit.setSelection(pos);
        }

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

        }
        Bundle bundle = getArguments();

        setUnitSpinner(unitHash, null);
        if (bundle != null && bundle.containsKey("ID")) {
            ID = bundle.getInt("ID");
            Unit = bundle.getInt("Unit");
            ProductName = bundle.getString("ProductName");
            MinRate = bundle.getFloat("MinRate");
            MaxRate = bundle.getFloat("MaxRate");

            binding.etProductName.setText(ProductName);
            binding.etMaxPrice.setText(String.valueOf(MaxRate));
            binding.etMinPrice.setText(String.valueOf(MinRate));
            binding.etProductName.setText(ProductName);
            binding.btnProductSave.setText("अपडेट");
            OperationType = "U";
            setUnitSpinner(unitHash, Unit);

        }

    }

    public interface saveProductListener {
        void onProductSave(String txtProductName, Integer txtUnitID, String txtMinPrice, String txtMaxPrice);

        void onProductUpdate(Integer ID, String txtProductName, Integer txtUnitID, String txtMinPrice, String txtMaxPrice);

    }
}