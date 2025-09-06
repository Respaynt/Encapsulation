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
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        return products.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();


    }

    public void printReceipt() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }
        long specialCount = products.values().stream()
                .flatMap(List::stream)
                .peek(System.out::println)
                .filter(Product::isSpecial)
                .count();


        System.out.println("Итого: " + getTotalPrice() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
    }


    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeByName(String name) {
        return products.containsKey(name) ? products.remove(name) : new ArrayList<>();

    }

}
