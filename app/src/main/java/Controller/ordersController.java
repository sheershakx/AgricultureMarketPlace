package Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import Interface.FarmerAPI;
import View.ConsumerView;
import View.OrderListView;
import View.ResultView;
import model.OrderList;
import model.Orders;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ordersController {
    Context context;
    ResultView resultView;
    OrderListView orderListView;

    public ordersController(Context context, ResultView resultView, OrderListView orderListView) {
        this.context = context;
        this.resultView = resultView;
        this.orderListView = orderListView;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.sheershakrg.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void createOrder(Orders orders) {
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<Result> call = farmerAPI.postOrders(orders.getPostID(), orders.getConsumerID(), orders.getFarmerID(), orders.getOrderDate(), orders.getQuantity(),
                orders.getHomedelivery(), orders.getAddress(), orders.getMobile(), orders.getStatus());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                resultView.responseReady(result);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("Error", t.getMessage());
                Toast.makeText(context, "Technical Error..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void GetOrderList(Integer ID, String UserType) {
        FarmerAPI farmerAPI = retrofit.create(FarmerAPI.class);
        Call<List<OrderList>> call = farmerAPI.getOrderList(ID, UserType);
        call.enqueue(new Callback<List<OrderList>>() {
            @Override
            public void onResponse(Call<List<OrderList>> call, Response<List<OrderList>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error code:" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<OrderList> orderList = response.body();
                orderListView.orderListReady(orderList);
            }

            @Override
            public void onFailure(Call<List<OrderList>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                Toast.makeText(context, "Technical Error..", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
