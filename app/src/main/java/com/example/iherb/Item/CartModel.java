package com.example.iherb.Item;

public class CartModel {
    String imgUrl;
    String name;
    String price;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CartModel(String id, String imgUrl, String name, String price) {
        this.imgUrl = imgUrl;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public CartModel() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
