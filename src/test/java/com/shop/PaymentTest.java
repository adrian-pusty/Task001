package com.shop;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.shop.CreditCardType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTest {

    @org.junit.jupiter.api.Test
    void withDiscount()
    {
        Product bike = Product.builder().name("bike").price(BigDecimal.valueOf(2000)).build();
        Product steamGiftCard = Product.builder().name("steam gift card").price(BigDecimal.valueOf(50)).build();
        Product amazonGiftCard = Product.builder().name("amazon gift card").price(BigDecimal.valueOf(100)).build();
        Product bubbleGum = Product.builder().name("bubble gum").price(BigDecimal.valueOf(0.55)).build();
        Product car = Product.builder().name("toyota car").price(BigDecimal.valueOf(10_000)).build();
        Product window = Product.builder().name("window").price(BigDecimal.valueOf(1500)).build();

        List<Product> products1 = Arrays.asList(bike, steamGiftCard, amazonGiftCard, bubbleGum);
        List<Product> products2 = Arrays.asList(steamGiftCard, amazonGiftCard, bubbleGum, car, window);
        List<Product> products3 = Arrays.asList(steamGiftCard, bubbleGum);

        testDiscount(products1, SILVER, new BigDecimal("1935.5"));
        testDiscount(products2, NORMAL, new BigDecimal("11650.55"));
        testDiscount(products3, GOLD, new BigDecimal("40.44"));
    }

    private void testDiscount(List<Product> products, CreditCardType creditCardType, BigDecimal expected)
    {
        ShoppingCart shoppingCart = new ShoppingCartImpl(products);
        Payment payment = PaymentImpl.builder()
                .creditCardType(creditCardType)
                .shoppingCart(shoppingCart).build();

        BigDecimal result = payment.withDiscount();

        assertEquals(0, result.compareTo(expected));
    }
}