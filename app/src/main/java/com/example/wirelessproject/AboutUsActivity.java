package com.example.wirelessproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.about);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    // Restart MainActivity to go back to the welcome screen
                    Intent intent = new Intent(AboutUsActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.menu) {
                    startActivity(new Intent(AboutUsActivity.this, ProductGalleryActivity.class));
                    return true;
                } else if (id == R.id.about) {
                    // Reload the same activity
                    startActivity(new Intent(AboutUsActivity.this, AboutUsActivity.class));
                    return true;
                } else if (id == R.id.shopping) {
                    startActivity(new Intent(AboutUsActivity.this, ShoppingCartActivity.class));
                    return true;
                } else if (id == R.id.contact) {
                    startActivity(new Intent(AboutUsActivity.this, ContactUsActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
