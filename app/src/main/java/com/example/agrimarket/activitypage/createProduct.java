package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.agrimarket.databinding.ActivityCreateProductBinding;

import java.util.HashMap;
import java.util.List;

import Adapters.productListAdapter;
import Controller.productController;
import model.Product;
import View.ProductView;
import View.UnitView;
import model.Result;
import model.Unit;
import View.ResultView;


public class createProduct extends AppCompatActivity implements createProductFragment.saveProductListener, ProductView, ResultView {

    ActivityCreateProductBinding binding;
    productController productController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        /*RETROFIT OPERATIONS*/
        productController = new productController(this, this, this);
        productController.getProduct();

        /** functions start **/
        binding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProductFragment productFragment = new createProductFragment();
                productFragment.show(getSupportFragmentManager(), "Product Fragment");
            }
        });
    }

    @Override
    public void onProductSave(String txtProductName, Integer txtUnitID, String txtMinPrice, String txtMaxPrice) {
        Product product = new Product();
        product.setName(txtProductName);
        product.setUnit(txtUnitID);
        product.setMinRate(Float.parseFloat(txtMinPrice));
        product.setMaxRate(Float.parseFloat(txtMaxPrice));
        product.setStatus(1);
        product.setUsername("admin");
        productController.postProduct(product);
    }

    @Override
    public void onProductUpdate(Integer ID, String txtProductName, Integer txtUnitID, String txtMinPrice, String txtMaxPrice) {
        Product product = new Product();
        product.setID(ID);
        product.setName(txtProductName);
        product.setUnit(txtUnitID);
        product.setMinRate(Float.parseFloat(txtMinPrice));
        product.setMaxRate(Float.parseFloat(txtMaxPrice));
        product.setStatus(1);
        product.setUsername("admin");
        productController.updateProduct(product);
    }


    @Override
    public void productReady(List<Product> products) {

        binding.rvProductList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProductList.setAdapter(new productListAdapter(products));

    }


    @Override
    public void responseReady(Result result) {
        Log.e("Status", result.getStatus());
        Log.e("Response", result.getResponse());
        if (result.getStatus().contentEquals("OK")) {
            Fragment productFragment = getSupportFragmentManager().findFragmentByTag("Product Fragment");
            if (productFragment != null) {
                DialogFragment dialogFragment = (DialogFragment) productFragment;
                dialogFragment.dismiss();
            }

            Toast.makeText(this, result.getResponse(), Toast.LENGTH_SHORT).show();

        }
    }
}