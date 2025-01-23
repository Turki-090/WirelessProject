package com.example.wirelessproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Get product details from intent
        String id = getIntent().getStringExtra("productId");
        String name = getIntent().getStringExtra("productName");
        String category = getIntent().getStringExtra("productCategory");
        String description = getIntent().getStringExtra("productDescription");
        double price = getIntent().getDoubleExtra("productPrice", 0.0);

        // Initialize views
        TextView nameTextView = findViewById(R.id.productDetailsName);
        TextView categoryTextView = findViewById(R.id.productDetailsCategory);
        TextView priceTextView = findViewById(R.id.productDetailsPrice);
        TextView descriptionTextView = findViewById(R.id.productDetailsDescription);
        Button addToCartButton = findViewById(R.id.addToCartButton);

        nameTextView.setText(name);
        categoryTextView.setText(category);
        priceTextView.setText("$" + price);
        descriptionTextView.setText(description);



        // Add to Cart button functionality
        addToCartButton.setOnClickListener(v -> {
            Product product = new Product(id, name, category, description, price, null);
            ShoppingCart.getInstance().addItem(product);
            Toast.makeText(this, name + " added to cart.", Toast.LENGTH_SHORT).show();

            // Optionally, navigate to cart
            startActivity(new Intent(ProductDetailsActivity.this, CartActivity.class));
        });
    }

}
