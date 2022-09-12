package com.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ShoppingCart
{
    void addProduct(Product product);
    void removeProduct(Product product);
    BigDecimal totalPrice();
}

class ShoppingCartImpl implements ShoppingCart
{
    private final List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product)
    {
        products.add(product);
    }

    @Override
    public void removeProduct(Product product)
    {
        products.remove(product);
    }

    @Override
    public BigDecimal totalPrice()
    {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
