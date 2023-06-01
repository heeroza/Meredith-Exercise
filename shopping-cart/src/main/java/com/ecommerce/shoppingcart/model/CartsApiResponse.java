package com.ecommerce.shoppingcart.model;

import lombok.Data;

import java.util.List;

@Data
public class CartsApiResponse {
    List<Cart> carts;
}
