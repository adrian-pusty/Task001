package com.shop;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ShoppingCart
{
    ShoppingCart addProduct(Product product);
    ShoppingCart removeProduct(Product product);
    BigDecimal totalPrice();
}

@NoArgsConstructor
@AllArgsConstructor
class ShoppingCartImpl implements ShoppingCart
{
    private List<Product> products = new ArrayList<>();

    @Override
    public ShoppingCart addProduct(Product product)
    {
        products.add(product);
        return this;
    }

    @Override
    public ShoppingCart removeProduct(Product product)
    {
        products.remove(product);
        return this;
    }

    @Override
    public BigDecimal totalPrice()
    {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
