package com.example.agrimarket.activitypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class postDetail extends AppCompatActivity {
    TextView ProductName, Unit, Quantity, Price, Stock, Location, Description, Homedelivery;
    Button Order;
    CoordinatorLayout BottomSheet;
    BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        //typecasting
        ProductName = findViewById(R.id.tvProductName);
        Unit = findViewById(R.id.tvUnit);
        Quantity = findViewById(R.id.tvQuantity);
        Price = findViewById(R.id.tvPrice);
        Stock = findViewById(R.id.tvStockQuantity);
        Location = findViewById(R.id.tvLocation);
        Description = findViewById(R.id.tvDescription);
        Homedelivery = findViewById(R.id.tvHomeDelivery);
        Order = findViewById(R.id.btnOrder);

        //coordinator layout bottom sheet
        BottomSheet = findViewById(R.id.orderBottomSheet);

        //bottom sheet behaviour setup
        sheetBehavior = BottomSheetBehavior.from(BottomSheet);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        /** Functions Start **/
        //order button onclick to toggle bottomsheet
        Order.setOnClickListener(new View.OnClickListener() {
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
}