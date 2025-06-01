package org.skypro.skyshop;

import org.skypro.skyshop.Product.*;
import org.skypro.skyshop.Product.Article.Article;
import org.skypro.skyshop.Product.Article.SearchEngine;
import org.skypro.skyshop.Product.Article.Searchable;
import org.skypro.skyshop.Product.SimpleProduct.SimpleProduct;
import org.skypro.skyshop.ProductBasket.ProductBasket;

public class App {
    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine(10);


        Product p1 = new SimpleProduct("Сыр", 250);
        Product p2 = new DiscountedProduct("Кофе", 360, 0.10);
        Product p3 = new FixPriceProduct("Торт");

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
        performSearch(engine, "вода"); // не должно найти

        System.out.println("Чек:");
        basket.printReceipt();

        basket.clearBasket();
    }

    private static void performSearch(SearchEngine engine, String query) {
        System.out.println("\nПоиск по запросу: " + query);
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





