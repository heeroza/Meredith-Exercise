package com.ecommerce.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer id;
    private List<CartItem> products;
    private Double total;
    private Double discountedTotal;
    private Integer userId;
    private Integer totalProducts;
    private Integer totalQuantity;
}
