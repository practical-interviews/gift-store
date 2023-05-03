package com.prime.giftstore.repo.model;

public class Order {
    private String id;
    private String productId;
    private Integer quantity;
    private Double totalPrice;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public Order setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Order setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
