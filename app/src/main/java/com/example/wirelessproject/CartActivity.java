package com.example.wirelessproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartActivity extends AppCompatActivity implements ShoppingCart.CartUpdateListener {

    private RecyclerView cartRecyclerView;
    private TextView totalPriceTextView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalCostTextView);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get cart items
        List<Product> cartItems = ShoppingCart.getInstance().getCartItems();

        // Set adapter
        cartAdapter = new CartAdapter(cartItems);
        cartRecyclerView.setAdapter(cartAdapter);

        // Set the listener for cart updates
        ShoppingCart.getInstance().setCartUpdateListener(this);

        // Calculate and display total cost
        calculateTotalCost();
    }


    private void calculateTotalCost() {
        double totalCost = 0.0;

        // Sum the prices of all products in the cart
        for (Product product : ShoppingCart.getInstance().getCartItems()) {
            totalCost += product.getPrice();
        }

        // Update the total price TextView
        totalPriceTextView.setText("Total Price: $" + totalCost);
    }

    @Override
    public void onCartUpdated() {
        calculateTotalCost(); // Dynamically recalculate the total cost
    }

}
