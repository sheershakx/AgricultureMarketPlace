package com.example.agrimarket.activitypage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.agrimarket.databinding.ActivityPostProductsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import Controller.productController;
import Controller.unitController;
import View.UnitView;
import View.ProductView;
import model.Product;
import model.Result;
import model.Unit;
import View.ResultView;

public class postProducts extends AppCompatActivity implements UnitView, ProductView, ResultView {
    ActivityPostProductsBinding binding;
    HashMap<Integer, String> uniHash = new HashMap<>();
    HashMap<Integer, String> productHash = new HashMap<>();
    List<Product> productCached = new ArrayList<>();
    private Integer[] unitValueArray, productValueArray;
    private String[] unitTextArray, productTextArray;
    public static float MinPrice, MaxPrice;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostProductsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        /** Functions Start  **/
        unitController unitController = new unitController(this, this);
        unitController.getUnit();
        productController productController = new productController(this, this, this);
        productController.getProduct();
        //change switch text when toggled.
        binding.toggleHomeDelivery.setShowText(true);
        binding.toggleHomeDelivery.setTextOn("छ");
        binding.toggleHomeDelivery.setTextOff("छैन");

        //btnsave onclick function
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: get value for spinners, not sure what value to pick, maybe ll use hashmap to store text and value and fetch value only

                String txtquantity = binding.etQuantity.getText().toString();
                String txtprice = binding.etPrice.getText().toString();
                String txtstock = binding.etStockQuantity.getText().toString();
                String txtlocation = binding.etLocation.getText().toString();
                String txtdescription = binding.etDescription.getText().toString();
                if (validateInputs(txtquantity, txtprice, txtstock, txtlocation, txtdescription)) {

                }
            }
        });

        binding.spProductName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer productID = Arrays.asList(productValueArray).get(position);     //get productID from product ID & ArrayPos String []
                for (Product product : productCached) {
                    if (product.getID() == productID) {
                        MinPrice = product.getMinRate();
                        MaxPrice = product.getMaxRate();
                        binding.tvPriceRange.setText("न्युनतम मुल्य: " + MinPrice + ", अधिकतम मुल्य: " + MaxPrice);
                        Integer unitID = product.getUnit();                             //get unitID from model
                        Integer unitPosition = Arrays.asList(unitValueArray).indexOf(unitID); //get position of unit corresponding unit value STring[]
                        binding.spUnit.setSelection(unitPosition);                           //set unit position



                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        binding.etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                /** TODO: Check empty or null text before  checking for brtween min price and max price **/
                float price = Float.parseFloat(binding.etPrice.getText().toString());
                if (!(price >= MinPrice && price <= MaxPrice)) {
                    binding.tvPriceError.setText("तपाइको मुल्य तोकिएको दायरा भित्र पर्दैन !!");
                } else binding.tvPriceError.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nothing here
            }
        });
    }

    public boolean validateInputs(String quantity, String price, String stock, String location, String description) {

        if (TextUtils.isEmpty(quantity)) {
            binding.etQuantity.requestFocus();
            binding.etQuantity.setError("परिमाण आवश्यक");

            return false;
        }
        if (TextUtils.isEmpty(price)) {
            binding.etPrice.requestFocus();
            binding.etPrice.setError("मुल्य आवश्यक");

            return false;

        }
        if (TextUtils.isEmpty(stock)) {
            binding.etStockQuantity.requestFocus();
            binding.etStockQuantity.setError("स्टक आवश्यक");

            return false;

        }
        if (TextUtils.isEmpty(location)) {
            binding.etLocation.requestFocus();
            binding.etLocation.setError("स्थान आवश्यक");
            return false;

        }
        if (TextUtils.isEmpty(description)) {
            binding.etDescription.requestFocus();
            binding.etDescription.setError("विवरण आवश्यक");

            return false;

        }
        return true;
    }


    @Override
    public void UnitReady(List<Unit> units) {
        int i = 0;
        for (Unit unit : units) {
            uniHash.put(unit.getID(), unit.getUnit());


            i++;
        }
        setUnitSpinner(uniHash);

    }

    private void setUnitSpinner(HashMap<Integer, String> hashMap) {
        int pos = 0;
        Collection<String> valuesCollection = hashMap.values();
        Collection<Integer> keyCollection = hashMap.keySet();
        unitTextArray = valuesCollection.toArray(new String[hashMap.size()]);

        unitValueArray = keyCollection.toArray(new Integer[hashMap.size()]);
        ArrayAdapter<String> unitadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitTextArray);
        unitadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spUnit.setAdapter(unitadapter);
    }

    private void setProductSpinner(HashMap<Integer, String> productHash) {
        Collection<String> valuesCollection = productHash.values();
        Collection<Integer> keyCollection = productHash.keySet();
        productTextArray = valuesCollection.toArray(new String[productHash.size()]);
        productValueArray = keyCollection.toArray(new Integer[productHash.size()]);
        ArrayAdapter<String> unitadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productTextArray);
        unitadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spProductName.setAdapter(unitadapter);
    }

    @Override
    public void productReady(List<Product> products) {
        productCached.addAll(products);
        int i = 0;
        for (Product product : products) {
            productHash.put(product.getID(), product.getName());


        }
        setProductSpinner(productHash);
    }

    @Override
    public void responseReady(Result result) {

    }
}

