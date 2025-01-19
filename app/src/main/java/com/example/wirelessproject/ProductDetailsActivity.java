package com.example.wirelessproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        TextView nameTextView = findViewById(R.id.productDetailsName);
        TextView categoryTextView = findViewById(R.id.productDetailsCategory);
        TextView priceTextView = findViewById(R.id.productDetailsPrice);
        TextView descriptionTextView = findViewById(R.id.productDetailsDescription);

        nameTextView.setText(name);
        categoryTextView.setText(category);
        priceTextView.setText("$" + price);
        descriptionTextView.setText(description);
    }
}
