package com.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum CreditCardType
{
    GOLD(new BigDecimal("20")),
    SILVER(BigDecimal.TEN),
    NORMAL(BigDecimal.ZERO);

    private final BigDecimal discountPercentage;
}
