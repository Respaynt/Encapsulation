package org.skypro.skyshop.Product;

import org.skypro.skyshop.Product.Article.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустой строкой или null");
        }
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
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