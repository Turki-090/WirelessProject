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
        cartItems.add(product);

        // Notify CartActivity to update total cost
        if (cartUpdateListener != null) {
            cartUpdateListener.onCartUpdated();
        }
    }

    public void removeItem(Product product) {
        cartItems.remove(product);

        // Notify CartActivity to update total cost
        if (cartUpdateListener != null) {
            cartUpdateListener.onCartUpdated();
        }
    }



    private CartUpdateListener cartUpdateListener;

    public interface CartUpdateListener {
        void onCartUpdated();
    }

    public void setCartUpdateListener(CartUpdateListener listener) {
        this.cartUpdateListener = listener;
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

    private void updateTotalCost() {
        double total = getTotalCost();
        databaseRef.child("totalCost").setValue(total);
    }

    public DatabaseReference getDatabaseRef() {
        return databaseRef;
    }
}
