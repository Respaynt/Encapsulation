package org.skypro.skyshop.ProductBasket;

import org.skypro.skyshop.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private Map<String, List<Product>> products;

    public ProductBasket() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.computeIfAbsent(product.getName(),k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }
        return total;
    }

    public void printReceipt() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }

        int specialCount = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                System.out.println(product);
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }

        System.out.println("Итого: " + getTotalPrice() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
    }

    public void clearBasket() {
        products.clear();
    }
    public List<Product> removeByName(String name) {
        return products.getOrDefault(name, new ArrayList<>());

    }

}
