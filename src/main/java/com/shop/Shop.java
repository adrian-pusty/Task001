package com.shop;

import java.math.BigDecimal;

import static com.shop.CreditCardType.SILVER;

public class Shop
{
    public static void main(String[] args) {
        CreditCardType cct = SILVER;
        ShoppingCart sc = new ShoppingCartImpl();
        sc.addProduct(Product.builder().name("bike").price(BigDecimal.valueOf(2000)).build());
        sc.addProduct(Product.builder().name("steam gift card").price(BigDecimal.valueOf(50)).build());
        sc.addProduct(Product.builder().name("amazon gift card").price(BigDecimal.valueOf(100)).build());
        sc.addProduct(Product.builder().name("bubble gum").price(BigDecimal.valueOf(0.55)).build());
        Payment pmt = PaymentImpl.builder().creditCardType(cct).shoppingCart(sc).build();
        System.out.println(pmt.withDiscount().doubleValue());
    }
}
