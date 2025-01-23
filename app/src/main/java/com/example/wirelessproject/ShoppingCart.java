package com.example.wirelessproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static ShoppingCart instance;
    private List<Product> cartItems;
    private DatabaseReference databaseRef;

    private ShoppingCart() {
        cartItems = new ArrayList<>();
        databaseRef = FirebaseDatabase.getInstance().getReference("cart");
    }

    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addItem(Product product) {
        if (product.getId() == null) {
            product.setId(databaseRef.push().getKey());
        }
        cartItems.add(product);
        databaseRef.child(product.getId()).setValue(product);
    }

    public void removeItem(Product product) {
        cartItems.remove(product);
        databaseRef.child(product.getId()).removeValue();
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public double getTotalCost() {
        double total = 0.0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }
}
