package com.example.iherb.Item;

public class CartModelFrag {
    String imgUrl;
    String name;
    String price;

    public CartModelFrag( String imgUrl, String name, String price) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
    }

    public CartModelFrag() {
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
