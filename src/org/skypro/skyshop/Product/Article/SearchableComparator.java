package org.skypro.skyshop.Product.Article;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable s1, Searchable s2) {
        String name1 = s1.getName();
        String name2 = s2.getName();

        // Сортировка по убыванию длины
        int lengthCompare = Integer.compare(name2.length(), name1.length());
        if (lengthCompare != 0) {
            return lengthCompare;
        }

        // Натуральная сортировка по имени
        return name1.compareTo(name2);
    }
}