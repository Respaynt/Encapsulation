package org.skypro.skyshop;

import org.skypro.skyshop.Product.Product;
import org.skypro.skyshop.ProductBasket.ProductBasket;

public class App {
    public static void main(String[] args){
        Product p1 = new Product("Хлеб", 60);
        Product p2 = new Product("Молоко", 75);
        Product p3 = new Product("Конфеты", 150);
        Product p4 = new Product("Сахар", 70);
        Product p5 = new Product("Чай", 80);
        Product p6 = new Product("Сыр", 250);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(p1);
        basket.addProduct(p2);
        basket.addProduct(p3);
        basket.addProduct(p4);
        basket.addProduct(p5);

        basket.addProduct(p6);

        basket.PrintBasket();

        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println("Есть ли Чай? " + basket.containsProduct("Чай"));
        System.out.println("Есть ли Кофе? " + basket.containsProduct("Кофе"));

        basket.clearBasket();

        basket.PrintBasket();

        System.out.println("Общая стоимость после очистки: " + basket.getTotalPrice());
        System.out.println("Есть ли Чай? " + basket.containsProduct("Чай"));
    }
}
