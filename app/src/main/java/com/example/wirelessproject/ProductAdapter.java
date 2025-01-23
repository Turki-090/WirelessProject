package com.example.wirelessproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnProductClickListener listener;

    // Interface for click handling
    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    public ProductAdapter(List<Product> productList, OnProductClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Get the current product
        Product product = productList.get(position);

        // Set data to the views
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText("$" + product.getPrice());
        holder.categoryTextView.setText(product.getCategory());

        // Add click listener for navigation to ProductDetailsActivity
        holder.itemView.setOnClickListener(v -> {
            // Create an Intent to navigate to ProductDetailsActivity
            Intent intent = new Intent(holder.itemView.getContext(), ProductDetailsActivity.class);

            // Pass product details to the new activity
            intent.putExtra("productName", product.getName());
            intent.putExtra("productCategory", product.getCategory());
            intent.putExtra("productPrice", product.getPrice());
            intent.putExtra("productDescription", product.getDescription());

            // Start the ProductDetailsActivity
            holder.itemView.getContext().startActivity(intent);
        });

        // Add click listener for the "Add to Cart" button
        holder.addToCartButton.setOnClickListener(v -> {
            ShoppingCart.getInstance().addItem(product);
            Toast.makeText(holder.itemView.getContext(), product.getName() + " added to cart.", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, categoryTextView;
        Button addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.productName);
            priceTextView = itemView.findViewById(R.id.productPrice);
            categoryTextView = itemView.findViewById(R.id.productCategory);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}
