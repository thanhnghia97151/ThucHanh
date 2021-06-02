package com.example.products;

public class Product {
    private String id;
    private String type;
    private String price;
    private String country;

    public Product(String type, String price, String country) {
        this.type = type;
        this.price = price;
        this.country = country;
    }

    public Product(String id, String type, String price, String country) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.country = country;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
