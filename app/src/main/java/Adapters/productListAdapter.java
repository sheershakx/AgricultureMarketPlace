package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.agrimarket.R;

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
//        for (Product product : product) {
//            final String productName = product.getName();
//            holder.tvListName.setText(productName);
//        }
        holder.tvListName.setText(product.get(position).getName());

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
