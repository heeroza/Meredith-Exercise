package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.model.Cart;
import com.ecommerce.shoppingcart.model.CartsApiResponse;
import com.ecommerce.shoppingcart.model.contract.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class CartServiceImplUnitTest {

    @Mock
    private RestTemplate restTemplate;

    private CartService<Cart> cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartServiceImpl(restTemplate);
    }

    @Test
    public void testGetAllCarts() {
        // Mock the API response
        CartsApiResponse apiResponse = new CartsApiResponse();
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(1, "User 1", 100.0));
        carts.add(new Cart(2, "User 2", 150.0));
        apiResponse.setCarts(carts);
        when(restTemplate.getForObject(anyString(), eq(CartsApiResponse.class))).thenReturn(apiResponse);

        // Call the method to test
        List<Cart> result = cartService.getAllCarts();

        // Assert the result
        assertEquals(2, result.size());
        // Add your assertions for each cart
        assertEquals(1, result.get(0).getId());
        assertEquals("User 1", result.get(0).getUser());
        assertEquals(100.0, result.get(0).getTotal(), 0.0);
        // Repeat the assertions for other carts
    }

    @Test
    public void testGetCart() {
        // Mock the API response
        Cart cart = new Cart(1, "User 1", 100.0);
        when(restTemplate.getForObject(anyString(), eq(Cart.class))).thenReturn(cart);

        // Call the method to test
        Cart result = cartService.getCart(1);

        // Assert the result
        assertEquals(1, result.getId());
        assertEquals("User 1", result.getUser());
        assertEquals(100.0, result.getTotal(), 0.0);
    }

    @Test
    public void testGetUserCarts() {
        // Mock the API response
        CartsApiResponse apiResponse = new CartsApiResponse();
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(1, "User 1", 100.0));
        apiResponse.setCarts(carts);
        when(restTemplate.getForObject(anyString(), eq(CartsApiResponse.class))).thenReturn(apiResponse);

        // Call the method to test
        List<Cart> result = cartService.getUserCarts(1);

        // Assert the result
        assertEquals(1, result.size());
        // Add your assertions for the user's cart
        assertEquals(1, result.get(0).getId());
        assertEquals("User 1", result.get(0).getUser());
        assertEquals(100.0, result.get(0).getTotal(), 0.0);
    }
}
