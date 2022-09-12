package com.shop;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
public class Product
{
    private String name;
    @Getter
    private BigDecimal price;
}
