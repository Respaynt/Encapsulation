package org.skypro.skyshop.Product.Article;

public class SearchEngine {private Searchable[] items;
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
        Searchable[] results = new Searchable[5];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (items[i].getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[count] = items[i];
                count++;
                if (count == 5) {
                    break;
                }
            }
        }

        return results;
    }
}
