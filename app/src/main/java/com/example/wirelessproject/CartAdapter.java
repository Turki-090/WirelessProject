package com.example.wirelessproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Product> cartItems;

    public CartAdapter(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText("$" + product.getPrice());

        // Remove item and navigate to ShoppingCartActivity
        holder.removeItemButton.setOnClickListener(v -> {
            // Remove the item from the cart
            ShoppingCart.getInstance().removeItem(product);

            // Redirect to ShoppingCartActivity
            Intent intent = new Intent(holder.itemView.getContext(), ShoppingCartActivity.class);
            holder.itemView.getContext().startActivity(intent);

            // Finish the current activity to refresh (optional)
            if (holder.itemView.getContext() instanceof CartActivity) {
                ((CartActivity) holder.itemView.getContext()).finish();
            }
        });
    }




    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView;
        Button removeItemButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.productName);
            priceTextView = itemView.findViewById(R.id.productPrice);
            removeItemButton = itemView.findViewById(R.id.removeItemButton);
        }
    }
}
