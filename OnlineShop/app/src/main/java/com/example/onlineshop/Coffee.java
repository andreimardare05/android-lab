package com.example.onlineshop;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;

public class Coffee {
    private String name;
    private String description;
    private float price;

    public Coffee(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }

    public String selectionString() {
        return "You have selected an " + this.name;
    }
}
