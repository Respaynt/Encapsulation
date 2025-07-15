package org.skypro.skyshop.Product.Article;

import org.skypro.skyshop.Product.SimpleProduct.BestResultNotFound;

import java.util.*;
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
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptySet();
        }

        return items.stream()
                .filter (item -> item.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым");
        }

        return items.stream()
                .max(Comparator.comparingInt(item ->
                        countOccurrences(item.getSearchTerm().toLowerCase(), query.toLowerCase())))
                .filter(item -> countOccurrences(item.getSearchTerm().toLowerCase(), query.toLowerCase()) > 0)
                .orElseThrow(() -> new BestResultNotFound("Лучшее совпадение не найдено для запроса: " + query));
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
