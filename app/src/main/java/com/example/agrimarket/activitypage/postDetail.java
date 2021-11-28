package com.example.agrimarket.activitypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityPostDetailBinding;
import com.example.agrimarket.databinding.OrderSheetLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

import Controller.ordersController;
import model.OrderList;
import model.Orders;
import model.Posts;
import View.ResultView;
import model.Result;
import View.OrderListView;

public  class postDetail extends AppCompatActivity implements ResultView, OrderListView {
    ActivityPostDetailBinding binding;

    BottomSheetBehavior sheetBehavior;
    CoordinatorLayout bottomSheetLayout;
    public Integer PostID, FarmerID, HomeDelivery;
    public Float Stock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostDetailBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        //defining controller
        ordersController ordersController = new ordersController(this, this, this);


        /* Fetching intent Extras and setting text*/
        if (getIntent() != null && getIntent().getExtras().containsKey("PostID")) {
            binding.tvProductName.setText(getIntent().getStringExtra("Productname"));
            binding.tvUnit.setText(getIntent().getStringExtra("Unitname"));
            binding.tvQuantity.setText(String.valueOf(getIntent().getFloatExtra("Quantity", 0)));
            binding.tvPrice.setText("रु: " + getIntent().getFloatExtra("Price", 0));
            binding.tvStockQuantity.setText(getIntent().getFloatExtra("Stock", 0) + " " + getIntent().getStringExtra("Unitname"));
            binding.tvLocation.setText(getIntent().getStringExtra("Location"));
            binding.tvDescription.setText(getIntent().getStringExtra("Description"));
            binding.tvLocation.setText(getIntent().getStringExtra("Location"));
            if (getIntent().getIntExtra("Homedelivery", 0) == 1) {
                binding.tvHomeDelivery.setText("छ");
            } else {
                binding.tvHomeDelivery.setText("छैन");
            }
            PostID = getIntent().getIntExtra("PostID", 0);
            FarmerID = getIntent().getIntExtra("FarmerID", 0);
            Stock = getIntent().getFloatExtra("Stock", 0);
            HomeDelivery = getIntent().getIntExtra("Homedelivery", 0);

        }

        /* bottom sheet behaviour setup*/
        bottomSheetLayout = findViewById(R.id.bottomSheet);   //use id of root layout not external layout file
        sheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        /*Functions of controls of bottom Sheet*/
        binding.bottomSheet.etOrderQuantity.setText("0");
        binding.bottomSheet.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float quantity = Float.valueOf(binding.bottomSheet.etOrderQuantity.getText().toString());
                if (quantity > 0) {
                    quantity = quantity - 1;
                    if (quantity < 0) {
                        return;
                    }
                    binding.bottomSheet.etOrderQuantity.setText(quantity.toString());
                }
            }
        });
        binding.bottomSheet.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float quantity = Float.valueOf(binding.bottomSheet.etOrderQuantity.getText().toString());

                quantity = quantity + 1;
                if (quantity > Stock) {
                    Toast.makeText(postDetail.this, "स्टक परिमाण भन्दा धेरै भयो !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.bottomSheet.etOrderQuantity.setText(quantity.toString());

            }
        });
        binding.bottomSheet.btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Quantity = binding.bottomSheet.etOrderQuantity.getText().toString();
                String Address = binding.bottomSheet.etOrderAddress.getText().toString();
                String Mobile = binding.bottomSheet.etOrderPhone.getText().toString();
                if (validateInput(Quantity, Address, Mobile)) {
                    Orders orders = new Orders();
                    orders.setPostID(PostID);
                    orders.setConsumerID(6);//TODO:bring from session while login
                    orders.setFarmerID(FarmerID);
                    orders.setOrderDate("2078-05-03"); //set from session
                    orders.setQuantity(Float.valueOf(Quantity));
                    orders.setAddress(Address);
                    orders.setMobile(Mobile);
                    orders.setHomedelivery(HomeDelivery);
                    orders.setStatus("P");
                    ordersController.createOrder(orders);

                }
            }
        });


        /*END::: Functions of controls of bottom Sheet*/


        /** Functions Start **/
        //order button onclick to toggle bottomsheet
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) //i.e bottom sheet is hidden
                {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) { // i.e bototm sheet is visible
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });

        //buttom sheet on state changed
        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                switch (newState) {

                    case BottomSheetBehavior.STATE_EXPANDED:
                        // Order.setText("Expanded");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        // Order.setText("Hidden");
                        break;

                    case BottomSheetBehavior.STATE_DRAGGING:
                        // Order.setText("Dragging");
                        break;

                    case BottomSheetBehavior.STATE_SETTLING:
                        // Order.setText("Settling");
                        break;


                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });
    }

    public boolean validateInput(String quantity, String address, String phone) {
        if (TextUtils.isEmpty(quantity)) {
            binding.bottomSheet.etOrderQuantity.setError("परिमाण हाल्नुहोस");
            binding.bottomSheet.etOrderQuantity.requestFocus();
            binding.bottomSheet.etOrderQuantity.setFocusable(true);
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            binding.bottomSheet.etOrderPhone.setError("सम्पर्क नं हाल्नुहोस");
            binding.bottomSheet.etOrderPhone.requestFocus();
            binding.bottomSheet.etOrderPhone.setFocusable(true);
            return false;
        }
        if (HomeDelivery == 1) {
            if (TextUtils.isEmpty(address)) {
                binding.bottomSheet.etOrderAddress.setError("ठेगाना हाल्नुहोस");
                binding.bottomSheet.etOrderAddress.requestFocus();
                binding.bottomSheet.etOrderAddress.setFocusable(true);
                return false;
            }
        }
        return true;
    }

    @Override
    public void responseReady(Result result) {
        Toast.makeText(this, result.getResponse(), Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void orderListReady(List<OrderList> orderList) {

    }
}