package org.skypro.skyshop.Product.Article;

    public interface Searchable {
        String getSearchTerm();

        String getContentType();

        String getName();

        default String getStringRepresentation() {
            return getName() + " — " + getContentType();
        }
    }

