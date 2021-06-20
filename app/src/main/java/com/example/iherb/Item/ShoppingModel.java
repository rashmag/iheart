package com.example.iherb.Item;

public class ShoppingModel {
    String urlImage;
    String price;
    String name;
    public ShoppingModel() {
    }

    public ShoppingModel(String urlImage, String price, String name) {
        this.urlImage = urlImage;
        this.price = price;
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
