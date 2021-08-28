package Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrimarket.R;

import java.util.List;

import model.OrderList;

public class orderListAdapter extends RecyclerView.Adapter<orderListAdapter.ViewHolder> {
    public orderListAdapter(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    List<OrderList> orderLists;

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.getorder_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        holder.consumername.setText(orderLists.get(position).getConsumername());
        holder.productname.setText(orderLists.get(position).getProductname());
        holder.date.setText(orderLists.get(position).getOrderDate());
        String orderstatus = orderLists.get(position).getStatus();
        if (orderstatus.contentEquals("P")) {
            holder.status.setText("● प्रक्रियामा");
            holder.status.setTextColor(context.getResources().getColor(R.color.teal_700));
        } else {
            holder.status.setText("");

        }

    }

    @Override
    public int getItemCount() {
        return orderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView consumername, productname, status, date;

        public ViewHolder(View itemView) {
            super(itemView);
            consumername = itemView.findViewById(R.id.tvOrderPersonName);
            productname = itemView.findViewById(R.id.tvOrderProduct);
            status = itemView.findViewById(R.id.tvOrderStatus);
            date = itemView.findViewById(R.id.tvOrderDate);
        }
    }
}
