package org.skypro.skyshop.ProductBasket;

import org.skypro.skyshop.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBasket {
    private List<Product> products;

    public ProductBasket() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void printReceipt() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }

        int specialCount = 0;
        for (Product product : products) {
            System.out.println(product);
            if (product.isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("Итого: " + getTotalPrice() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
    }

    public void clearBasket() {
        products.clear();
    }
    public List<Product> removeByName(String name) {
        List<Product> removed = new ArrayList<>();
        products.removeIf(product -> {
            if (product.getName().equalsIgnoreCase(name)) {
                removed.add(product);
                return true;
            }
            return false;
        });
        return removed;
    }

}
