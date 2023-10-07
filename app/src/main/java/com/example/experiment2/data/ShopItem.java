package com.example.experiment2.data;

public class ShopItem {

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
    private final String name;
    private final double price;

    public ShopItem(String name_, double price_, int imageResouceid_) {
        this.name=name_;
        this.price=price_;
        this.imageResouceid =imageResouceid_;
    }
}
