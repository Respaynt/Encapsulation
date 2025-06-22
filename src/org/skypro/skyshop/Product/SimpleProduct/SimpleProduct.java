package org.skypro.skyshop.Product.SimpleProduct;

import org.skypro.skyshop.Product.Product;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String name, int price) {
        super(name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0. Текущая цена: " + price);
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " руб.";
    }
}