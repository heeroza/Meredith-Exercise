package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.model.Cart;
import com.ecommerce.shoppingcart.model.CartsApiResponse;
import com.ecommerce.shoppingcart.model.contract.CartService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
public class CartServiceImpl implements CartService<Cart> {

    private static final String API_BASE_URL = "https://dummyjson.com";

    private final RestTemplate restTemplate;

    @Autowired
    public CartServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Cart> getAllCarts() {
        String url = API_BASE_URL + "/carts";
        CartsApiResponse cartsApiResponse = restTemplate.getForObject(url, CartsApiResponse.class);
        return cartsApiResponse.getCarts();
    }

    @Override
    public Cart getCart(Integer cartId) {
        String url = API_BASE_URL + "/carts/" + cartId;
        return restTemplate.getForObject(url, Cart.class);
    }

    @Override
    public List<Cart> getUserCarts(Integer userId) {
        String url = API_BASE_URL + "/carts/user/" + userId;
        CartsApiResponse cartsApiResponse = restTemplate.getForObject(url, CartsApiResponse.class);
        return cartsApiResponse.getCarts();
    }
}
