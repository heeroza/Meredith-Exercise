package com.ecommerce.shoppingcart.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CartItem extends Product {
    private Integer quantity;
    private Double total;
    private Double discountedPrice;

    public CartItem(Integer id, String title, Double price, Integer quantity, Double total, Double discountedPrice,  Double discountPercentage) {
        super(id, title, price, discountPercentage);
        this.quantity = quantity;
        this.total = total;
        this.discountedPrice = discountedPrice;
    }
}
