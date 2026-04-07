package com.emp.shared.models;

public class Order {

    private long id;
    private String product;
    private int quantity;

    public Order(long id, String product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() { return id; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
}