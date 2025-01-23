package com.example.wirelessproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ProductGalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_gallery);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize product list and adapter
        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList, product -> {
            // Handle product click
            Toast.makeText(this, "Clicked: " + product.getName(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);

        // Fetch products from Firebase
        fetchProductsFromFirebase();

        // Initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(ProductGalleryActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.menu) {
                    startActivity(new Intent(ProductGalleryActivity.this, ProductGalleryActivity.class));
                    return true;
                } else if (id == R.id.about) {
                    startActivity(new Intent(ProductGalleryActivity.this, AboutUsActivity.class));
                    return true;
                } else if (id == R.id.shopping) {
                    startActivity(new Intent(ProductGalleryActivity.this, ShoppingCartActivity.class));
                    return true;
                } else if (id == R.id.contact) {
                    startActivity(new Intent(ProductGalleryActivity.this, ContactUsActivity.class));
                    return true;
                }
                return false;
            }
        });

        // Add View Cart Button
        Button viewCartButton = findViewById(R.id.viewCartButton);
        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProductGalleryActivity.this, ShoppingCartActivity.class);
            startActivity(intent);
        });
    }

    private void fetchProductsFromFirebase() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("products");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    if (product != null) {
                        productList.add(product);
                        Log.d("Firebase", "Product fetched: " + product.getName());
                    } else {
                        Log.w("Firebase", "Null product fetched");
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to fetch products", error.toException());
                Toast.makeText(ProductGalleryActivity.this, "Failed to fetch products.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
