package org.skypro.skyshop.Product.Article;

import org.skypro.skyshop.Product.SimpleProduct.BestResultNotFound;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items;

    public SearchEngine() {
        this.items = new HashSet<>();
    }

    public void add(Searchable item) {
        items.add(item);
    }

    public Set<Searchable> search(String query) {
        List<Searchable> results = items.stream()
                .filter(item -> item.getClass().getSimpleName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        return Set.of();
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым");
        }

        int maxCount = -1;
        Searchable bestMatch = null;

        for (Searchable item : items) {
            String term = item.getSearchTerm();
            int count = countOccurrences(term.toLowerCase(), query.toLowerCase());
            if (count > maxCount) {
                maxCount = count;
                bestMatch = item;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound("Лучшее совпадение не найдено для запроса: " + query);
        }

        return bestMatch;
    }

    private int countOccurrences(String text, String pattern) {
        if (text == null || pattern == null || pattern.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(pattern, index)) != -1) {
            count++;
            index += pattern.length();
        }
        return count;
    }
}
