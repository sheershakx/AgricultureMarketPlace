package Adapters;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrimarket.R;
import com.example.agrimarket.activitypage.createFarmerFragment;
import com.example.agrimarket.activitypage.createProductFragment;

import java.util.List;

import model.Farmer;

public class farmerListAdapter extends RecyclerView.Adapter<farmerListAdapter.ViewHolder> {
    private List<Farmer> farmers;

    public farmerListAdapter(List<Farmer> farmers) {
        this.farmers = farmers;
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
        holder.tvFarmerView.setText(farmers.get(position).getFullname());
        holder.tvFarmerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle bundle = new Bundle();
                bundle.putInt("ID", farmers.get(position).getID());
                bundle.putInt("Status", farmers.get(position).getStatus());
                bundle.putString("Name", String.valueOf(farmers.get(position).getFullname()));
                bundle.putString("Address", String.valueOf(farmers.get(position).getAddress()));
                bundle.putString("Mobile", String.valueOf(farmers.get(position).getMobile()));
                createFarmerFragment farmerFragment = new createFarmerFragment();
                farmerFragment.setArguments(bundle);

                farmerFragment.show(activity.getSupportFragmentManager(), "FarmerFragment");
            }
        });

    }

    @Override
    public int getItemCount() {
        return farmers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFarmerView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFarmerView = itemView.findViewById(R.id.tvListName);
        }
    }
}
