package org.skypro.skyshop.Product.Article;

import org.skypro.skyshop.Product.SimpleProduct.BestResultNotFound;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchEngine {
    private List<Searchable> items;

    public SearchEngine() {
        this.items = new ArrayList<>();
    }

    public void add(Searchable item) {
        items.add(item);
    }

    public Map<String, Searchable> search(String query) {
        return items.stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .sorted(Comparator.comparing(Searchable::getName))
                .collect(Collectors.toMap(
                        Searchable::getName,
                        Function.identity(),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
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
