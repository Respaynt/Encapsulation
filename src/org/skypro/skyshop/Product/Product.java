package org.skypro.skyshop.Product;

import org.skypro.skyshop.Product.Article.Searchable;

public abstract class Product implements Searchable {
    private final String name;


    public Product(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public abstract String toString();
}

