package Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrimarket.R;
import com.example.agrimarket.activitypage.createProduct;
import com.example.agrimarket.activitypage.createProductFragment;

import java.util.List;

import model.Product;
import model.Unit;

public class productListAdapter extends RecyclerView.Adapter<productListAdapter.ViewHolder> {
    private List<Product> product;

    public productListAdapter(List<Product> product) {
        this.product = product;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.singlelistlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        holder.tvListName.setText(product.get(position).getName());
        holder.tvListName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle bundle = new Bundle();
                bundle.putInt("ID", product.get(position).getID());
                bundle.putInt("Unit", product.get(position).getUnit());
                bundle.putString("ProductName", product.get(position).getName());
                bundle.putFloat("MinRate", product.get(position).getMinRate());
                bundle.putFloat("MaxRate", product.get(position).getMaxRate());
                createProductFragment productFragment = new createProductFragment();
                productFragment.setArguments(bundle);

                productFragment.show(activity.getSupportFragmentManager(), "Product Fragment");

            }
        });

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvListName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvListName = itemView.findViewById(R.id.tvListName);
        }
    }
}
