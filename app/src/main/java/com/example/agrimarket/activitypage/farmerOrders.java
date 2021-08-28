package com.example.agrimarket.activitypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.ActivityFarmerOrdersBinding;

import java.util.List;

import Adapters.orderListAdapter;
import Controller.ordersController;
import View.ResultView;
import View.OrderListView;
import model.OrderList;
import model.Result;

public class farmerOrders extends AppCompatActivity implements ResultView, OrderListView {
    ActivityFarmerOrdersBinding binding;
    ordersController ordersController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFarmerOrdersBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        /*********************============== FUNCTIONS================******************************/
        ordersController = new ordersController(this, this, this);
        ordersController.GetOrderList(1,"F"); //TODO: use farmer id from login here
    }

    @Override
    public void orderListReady(List<OrderList> orderList) {
        binding.rvOrderList.setLayoutManager(new LinearLayoutManager(this));
        orderListAdapter orderListAdapter = new orderListAdapter(orderList);
        binding.rvOrderList.setAdapter(orderListAdapter);

    }

    @Override
    public void responseReady(Result result) {

    }
}