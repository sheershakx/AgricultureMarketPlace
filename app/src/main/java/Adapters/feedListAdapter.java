package Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrimarket.BuildConfig;
import com.example.agrimarket.R;
import com.example.agrimarket.activitypage.postDetail;

import java.util.List;

import model.Posts;

public class feedListAdapter extends RecyclerView.Adapter<feedListAdapter.ViewHolder> {
    private List<Posts> posts;

    public feedListAdapter(List<Posts> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.viewpostlayout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        //Toast.makeText(context, "a"+String.valueOf(posts.get(position).getProduct()), Toast.LENGTH_SHORT).show();
        holder.productName.setText(String.valueOf(posts.get(position).getProductname()));

        holder.quantity.setText("परिमाण: " + posts.get(position).getQuantity() + " " + posts.get(position).getUnitName());
        holder.price.setText("मुल्य रु " + posts.get(position).getPrice());
        if (posts.get(position).getHomeDelivery() == 1) {
            holder.homedelivery.setText("होम डेलिभरी: छ");
        } else holder.homedelivery.setText("होम डेलिभरी: छैन");

        /**Feed on click open Description in new page**/
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, postDetail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("PostID", posts.get(position).getPostID());
                bundle.putString("Farmername", posts.get(position).getFarmername());
                bundle.putInt("FarmerID", posts.get(position).getFarmerID());
                bundle.putString("Productname", posts.get(position).getProductname());
                bundle.putString("Unitname", posts.get(position).getUnitName());
                bundle.putFloat("Price", posts.get(position).getPrice());
                bundle.putFloat("Quantity", posts.get(position).getQuantity());
                bundle.putFloat("Stock", posts.get(position).getStock());
                bundle.putString("Location", posts.get(position).getLocation());
                bundle.putString("Description", posts.get(position).getDescription());
                bundle.putInt("Homedelivery", posts.get(position).getHomeDelivery());


                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, unit, price, quantity, homedelivery;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tvProductName);
            unit = itemView.findViewById(R.id.tvUnit);
            price = itemView.findViewById(R.id.tvPrice);
            quantity = itemView.findViewById(R.id.tvQuantity);
            homedelivery = itemView.findViewById(R.id.tvHomeDelivery);
            cardView = itemView.findViewById(R.id.cvFeedLayout);
        }
    }
}
