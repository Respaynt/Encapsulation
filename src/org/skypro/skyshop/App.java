package org.skypro.skyshop;

import org.skypro.skyshop.Product.*;
import org.skypro.skyshop.Product.Article.Article;
import org.skypro.skyshop.Product.Article.SearchEngine;
import org.skypro.skyshop.Product.Article.Searchable;
import org.skypro.skyshop.Product.SimpleProduct.BestResultNotFound;
import org.skypro.skyshop.Product.SimpleProduct.SimpleProduct;
import org.skypro.skyshop.ProductBasket.ProductBasket;

public class App {
    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine(10);

        Product p1 = new SimpleProduct("Сыр", 250);
        Product p2 = new DiscountedProduct("Кофе", 360, 0.10);
        Product p3 = new FixPriceProduct("Торт");

        try {
            Product p4 = new SimpleProduct(null, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }

        try {
            Product p5 = new SimpleProduct("   ", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }

        try {
            SimpleProduct p6 = new SimpleProduct("Товар", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании SimpleProduct: " + e.getMessage());
        }

        try {
            SimpleProduct p7 = new SimpleProduct("Товар", -10);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании SimpleProduct: " + e.getMessage());
        }

        try {
            DiscountedProduct p8 = new DiscountedProduct("Скидочный товар", 100, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании DiscountedProduct: " + e.getMessage());
        }

        try {
            DiscountedProduct p9 = new DiscountedProduct("Скидочный товар", 100, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании DiscountedProduct: " + e.getMessage());
        }

        ProductBasket basket = new ProductBasket();
        basket.addProduct(p1);
        basket.addProduct(p2);
        basket.addProduct(p3);

        engine.add((Searchable) p1);
        engine.add((Searchable) p2);
        engine.add((Searchable) p3);

        Article a1 = new Article("Полезные продукты", "Сыр и овощи — важная часть рациона.");
        Article a2 = new Article("Кофейные тренды", "Новый сорт кофе покоряет рынок.");
        Article a3 = new Article("Десерты", "Торт — лучшее завершение обеда.");

        engine.add(a1);
        engine.add(a2);
        engine.add(a3);

        performSearch(engine, "сыр");
        performSearch(engine, "кофе");
        performSearch(engine, "торт");
        performSearch(engine, "вода");

        System.out.println("Чек:");
        basket.printReceipt();

        basket.clearBasket();


        String searchQueryExisting = "Сыр и овощи";
        try {
            Searchable result = engine.findBestMatch(searchQueryExisting);
            System.out.println("Наиболее подходящий результат: " + result.getSearchTerm());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        String searchQueryMissing = "C++";
        try {
            Searchable result = engine.findBestMatch(searchQueryMissing);
            System.out.println("Наиболее подходящий результат: " + result.getSearchTerm());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение: " + e.getMessage());
        }
    }


    private static void performSearch(SearchEngine engine, String query) {
        System.out.println("Поиск по запросу: " + query);
        Searchable[] results = engine.search(query);
        boolean found = false;
        for (Searchable result : results) {
            if (result != null) {
                System.out.println(result.getStringRepresentation());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Ничего не найдено.");
        }
    }
}







