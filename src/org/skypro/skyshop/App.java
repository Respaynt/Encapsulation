package org.skypro.skyshop;

import org.skypro.skyshop.Product.*;
import org.skypro.skyshop.Product.SimpleProduct.SimpleProduct;
import org.skypro.skyshop.ProductBasket.ProductBasket;

public class App {
    public static void main(String[] args) {

        Product p1 = new SimpleProduct("Сыр", 250);
        Product p2 = new DiscountedProduct("Кофе", 360, 0.10);
        Product p3 = new FixPriceProduct("Торт");

        ProductBasket basket = new ProductBasket();

        basket.addProduct(p1);
        basket.addProduct(p2);
        basket.addProduct(p3);


        System.out.println(" Чек:");
        basket.printReceipt();


        basket.clearBasket();

    }
}





