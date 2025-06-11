package org.skypro.skyshop.ProductBasket;

import org.skypro.skyshop.Product.Product;

public class ProductBasket {
    private final Product[] products = new Product[5];
    private int count = 0;

    public void addProduct(Product product) {
        if (count >= products.length) {
            System.out.println("Корзина переполнена");
            return;
        }
        products[count++] = product;
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    public void printReceipt() {
        if (count == 0) {
            System.out.println("Корзина пуста");
            return;
        }

        int specialCount = 0;

        for (int i = 0; i < count; i++) {
            System.out.println(products[i]);
            if (products[i].isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("Итого: " + getTotalPrice() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
    }

    public void clearBasket() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }


}