package com.example.wirelessproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Get product details passed from the ProductGalleryActivity
        String name = getIntent().getStringExtra("productName");
        String category = getIntent().getStringExtra("productCategory");
        double price = getIntent().getDoubleExtra("productPrice", 0.0);
        String description = getIntent().getStringExtra("productDescription");

        // Display product details
        TextView nameTextView = findViewById(R.id.productName);
        TextView categoryTextView = findViewById(R.id.productCategory);
        TextView priceTextView = findViewById(R.id.productPrice);
        TextView descriptionTextView = findViewById(R.id.productDescription); // Corrected ID

        nameTextView.setText(name);
        categoryTextView.setText(category);
        priceTextView.setText("$" + price);
        descriptionTextView.setText(description);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    // Restart ProductGalleryActivity to go back to the welcome screen
                    Intent intent = new Intent(ProductDetailsActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.menu) {
                    startActivity(new Intent(ProductDetailsActivity.this, ProductGalleryActivity.class));
                    return true;
                } else if (id == R.id.about) {
                    startActivity(new Intent(ProductDetailsActivity.this, AboutUsActivity.class));
                    return true;
                } else if (id == R.id.shopping) {
                    // Reload the same activity
                    startActivity(new Intent(ProductDetailsActivity.this, ShoppingCartActivity.class));
                    return true;
                } else if (id == R.id.contact) {
                    startActivity(new Intent(ProductDetailsActivity.this, ContactUsActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
