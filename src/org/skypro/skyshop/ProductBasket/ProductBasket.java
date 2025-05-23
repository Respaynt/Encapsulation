package org.skypro.skyshop.ProductBasket;

import org.skypro.skyshop.Product.Product;

public class ProductBasket {
    public final Product[] products = new Product[5];
    private int coint = 0;

    public void addProduct(Product product) {
        if (coint >= products.length) {
            System.out.println("Корзина переполнена");
            return;
        }
        products[coint++] = product;

    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < coint; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    public void PrintBasket() {
        if (coint == 0) {
            System.out.println("Корзина пустая");
            return;
        }
        for (int i = 0; i < coint; i++) {
            System.out.println(products[i].getName() + ": " + products[i].getPrice());

        }
        System.out.println("Итого: " + getTotalPrice());

    }

    public boolean containsProduct(String name) {
        for (int i = 0; i < coint; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < coint; i++) {
            products[i] = null;
        }
        coint = 0;
    }
}
