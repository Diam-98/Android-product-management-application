package diam.app.applicationproduit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    ArrayList<ProductModel> productList;

    public ProductAdapter(Context context, ArrayList<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_design, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        holder.productId.setText(String.valueOf(productList.get(position).getIdProduit()));
        holder.productName.setText(String.valueOf(productList.get(position).getNomProduit()));
        holder.productCategory.setText(String.valueOf(productList.get(position).getCategorieProduit()));
        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetilsActivity.class);
                intent.putExtra("prodId", String.valueOf(productList.get(holder.getAdapterPosition()).getIdProduit()));
                intent.putExtra("productName", String.valueOf(productList.get(holder.getAdapterPosition()).getNomProduit()));
                intent.putExtra("productcategory", String.valueOf(productList.get(holder.getAdapterPosition()).getCategorieProduit()));
                intent.putExtra("productPrice", String.valueOf(productList.get(holder.getAdapterPosition()).getPrixProduit()));
                intent.putExtra("productDescription", String.valueOf(productList.get(holder.getAdapterPosition()).getDescriptionProduit()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productId, productName, productCategory;
        ConstraintLayout cl;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productId = itemView.findViewById(R.id.itemNumber);
            productName = itemView.findViewById(R.id.productName);
            productCategory = itemView.findViewById(R.id.categorieProduit);
            cl = itemView.findViewById(R.id.mainLayout);

        }
    }

}
