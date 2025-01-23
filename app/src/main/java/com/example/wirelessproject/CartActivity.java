package com.example.wirelessproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private TextView totalPriceTextView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get cart items
        List<Product> cartItems = ShoppingCart.getInstance().getCartItems();
        double totalPrice = ShoppingCart.getInstance().getTotalCost();

        // Log the cart items to debug
        for (Product product : cartItems) {
            Log.d("CartActivity", "Product in cart: " + product.getName());
        }

        // Set adapter
        cartAdapter = new CartAdapter(cartItems);
        cartRecyclerView.setAdapter(cartAdapter);

        // Update total price
        totalPriceTextView.setText("Total Price: $" + totalPrice);
    }
}
