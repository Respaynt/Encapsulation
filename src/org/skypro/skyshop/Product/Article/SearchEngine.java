package org.skypro.skyshop.Product.Article;

import org.skypro.skyshop.Product.SimpleProduct.BestResultNotFound;

public class SearchEngine {
    private Searchable[] items;
    private int size;

    public SearchEngine(int capacity) {
        items = new Searchable[capacity];
        size = 0;
    }

    public void add(Searchable item) {
        if (size < items.length) {
            items[size] = item;
            size++;
        } else {
            System.out.println("Переполнен");
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[size];  // Массив только нужного размера
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (items[i].getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[count] = items[i];
                count++;
                if (count == results.length) {
                    break;
                }
            }
        }

        Searchable[] finalResults = new Searchable[count];
        System.arraycopy(results, 0, finalResults, 0, count);
        return finalResults;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isEmpty()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым");
        }

        int maxCount = -1;
        Searchable bestMatch = null;

        for (int i = 0; i < size; i++) {
            String term = items[i].getSearchTerm();
            int count = countOccurrences(term.toLowerCase(), search.toLowerCase());
            if (count > maxCount) {
                maxCount = count;
                bestMatch = items[i];
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound("Лучшее совпадение не найдено для запроса: " + search);
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