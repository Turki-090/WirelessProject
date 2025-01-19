package com.example.wirelessproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigate to Product Gallery
        Button productGalleryButton = findViewById(R.id.productGalleryButton);
        productGalleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductGalleryActivity.class);
            startActivity(intent);
        });

        // Navigate to About Us page
        Button aboutUsButton = findViewById(R.id.aboutUsButton);
        aboutUsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });

        // Navigate to Contact Us page
        Button contactUsButton = findViewById(R.id.contactUsButton);
        contactUsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactUsActivity.class);
            startActivity(intent);
        });
    }
}
