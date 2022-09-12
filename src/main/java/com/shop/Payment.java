package com.shop;

import lombok.Builder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public interface Payment
{
    BigDecimal withDiscount();
}

@Builder
class PaymentImpl implements Payment
{
    private static final MathContext MATH_CONTEXT = new MathContext(2);
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private CreditCardType creditCardType;
    private ShoppingCart shoppingCart;

    @Override
    public BigDecimal withDiscount()
    {
        BigDecimal fraction = BigDecimal.ONE.subtract(creditCardType
                        .getDiscountPercentage()
                        .divide(HUNDRED, MATH_CONTEXT));

        return shoppingCart.totalPrice()
                .multiply(fraction)
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
