package org.skypro.skyshop;

import org.skypro.skyshop.Product.*;
import org.skypro.skyshop.Product.Article.Article;
import org.skypro.skyshop.Product.Article.SearchEngine;
import org.skypro.skyshop.Product.Article.Searchable;
import org.skypro.skyshop.Product.SimpleProduct.BestResultNotFound;
import org.skypro.skyshop.Product.SimpleProduct.SimpleProduct;
import org.skypro.skyshop.ProductBasket.ProductBasket;

import java.util.List;

public class App {
    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine();

        Product p1 = new SimpleProduct("Сыр", 250);
        Product p2 = new DiscountedProduct("Кофе", 365, 0.10);
        Product p3 = new FixPriceProduct("Торт");

        ProductBasket basket = new ProductBasket();

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

        System.out.println("Удаление продукта 'Сыр' из корзины:");
        List<Product> removedProducts = basket.removeByName("Сыр");
        if (removedProducts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Удаленные продукты:");
            for (Product product : removedProducts) {
                System.out.println(product);
            }
        }

        System.out.println("Содержимое корзины после удаления:");
        basket.printReceipt();

        System.out.println("Удаление продукта 'Молоко' из корзины:");
        List<Product> noProducts = basket.removeByName("Молоко");
        if (noProducts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Удаленные продукты:");
            for (Product product : noProducts) {
                System.out.println(product);
            }
        }

        System.out.println("Содержимое корзины после попытки удаления:");
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
        List<Searchable> results = engine.search(query);
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
            return;
        }
        for (Searchable result : results) {
            System.out.println(result.getStringRepresentation());
        }
    }
}

