package com.example.experiment2.data;

import java.io.Serializable;//序列化

public class ShopItem implements Serializable { //对象实现序列化

    public int getImageResouceid() {
        return imageResouceid;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    private final int imageResouceid;
    private  String name;
    private  double price;

    public ShopItem(String name_, double price_, int imageResouceid_) {
        this.name=name_;
        this.price=price_;
        this.imageResouceid =imageResouceid_;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

}
