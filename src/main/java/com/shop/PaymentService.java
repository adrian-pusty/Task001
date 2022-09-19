package com.shop;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.math.MathContext.DECIMAL128;

public interface PaymentService
{
    PaymentService addProduct(Product product);
    PaymentService removeProduct(Product product);
    BigDecimal pay();
}

@RequiredArgsConstructor
class CreditCardPayment implements PaymentService
{
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private final CreditCardType creditCardType;
    private final List<Product> products;

    @Override
    public CreditCardPayment addProduct(Product product)
    {
        products.add(product);
        return this;
    }

    @Override
    public CreditCardPayment removeProduct(Product product)
    {
        products.remove(product);
        return this;
    }

    @Override
    public BigDecimal pay()
    {
        BigDecimal discount = BigDecimal.ONE.subtract(creditCardType
                        .getDiscountPercentage()
                        .divide(HUNDRED, DECIMAL128));

        return priceWithoutDiscount()
                .multiply(discount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal priceWithoutDiscount()
    {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

class PayPalPayment implements PaymentService
{
    @Override
    public BigDecimal pay()
    {
        return null; //todo implement
    }

    @Override
    public PaymentService addProduct(Product product)
    {
        return null; //todo implement
    }

    @Override
    public PaymentService removeProduct(Product product)
    {
        return null; //todo implement
    }
}
