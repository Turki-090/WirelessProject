package com.example.wirelessproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView totalCostTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // Initialize RecyclerView
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize total cost TextView
        totalCostTextView = findViewById(R.id.totalCostTextView );

        // Fetch cart items and total cost
        List<Product> cartItems = ShoppingCart.getInstance().getCartItems();
        double totalCost = ShoppingCart.getInstance().getTotalCost();

        // Set up the adapter
        cartAdapter = new CartAdapter(cartItems);
        cartRecyclerView.setAdapter(cartAdapter);

        // Display total cost
        totalCostTextView.setText("Total Cost: $" + totalCost);

        // BottomNavigationView setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.shopping);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.menu) {
                    startActivity(new Intent(ShoppingCartActivity.this, ProductGalleryActivity.class));
                    return true;
                } else if (id == R.id.about) {
                    startActivity(new Intent(ShoppingCartActivity.this, AboutUsActivity.class));
                    return true;
                } else if (id == R.id.shopping) {
                    startActivity(new Intent(ShoppingCartActivity.this, ShoppingCartActivity.class));
                    return true;
                } else if (id == R.id.contact) {
                    startActivity(new Intent(ShoppingCartActivity.this, ContactUsActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
