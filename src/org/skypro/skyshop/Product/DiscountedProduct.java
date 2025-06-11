package org.skypro.skyshop.Product;

public class DiscountedProduct extends Product {
    private int basePrice;
    private double discount;

    public DiscountedProduct(String name, int basePrice, double discount) {
        super(name);
        this.basePrice = basePrice;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Текущий процент: " + discount);
        }
        this.discount = discount;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public int getPrice() {
        return (int) (basePrice * (1 - discount));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + " (со скидкой " + (int) (discount * 100) + "%) - " +
                getPrice() + " руб. (старая цена: " + basePrice + " руб.)";
    }
}

