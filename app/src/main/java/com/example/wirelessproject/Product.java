package com.example.wirelessproject;

public class Product {
    private String id;
    private String name;
    private String category;
    private String description;
    private double price;

    public Product() {
        // Default constructor required for Firebase
    }

    public Product(String id, String name, String category, String description, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Product(String id, String name, String category, String description, double price, Object o) {
    }

    // Getter methods to access product details
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}
