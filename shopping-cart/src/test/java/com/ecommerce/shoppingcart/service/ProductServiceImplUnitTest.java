package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.model.ProductsApiResponse;
import com.ecommerce.shoppingcart.model.contract.ProductService;
import com.ecommerce.shoppingcart.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ProductServiceImplUnitTest {

    @Mock
    private RestTemplate restTemplate;

    private ProductService<Product, String> productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(restTemplate);
    }

    @Test
    public void testGetAllProducts() {
        // Mock the API response
        ProductsApiResponse apiResponse = new ProductsApiResponse();
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product 1", 4.2));
        products.add(new Product(2, "Product 2", 3.8));
        apiResponse.setProducts(products);
        when(restTemplate.getForObject(anyString(), eq(ProductsApiResponse.class))).thenReturn(apiResponse);

        // Call the method to test
        List<Product> result = productService.getAllProducts();

        // Assert the result
        assertEquals(2, result.size());
        // Add your assertions for each product
        assertEquals(1, result.get(0).getId());
        assertEquals("Product 1", result.get(0).getTitle());
        assertEquals(4.2, result.get(0).getRating(), 0.0);
        // Repeat the assertions for other products
    }

    @Test
    public void testGetProduct() {
        // Mock the API response
        Product product = new Product(1, "Product 1", 4.2);
        when(restTemplate.getForObject(anyString(), eq(Product.class))).thenReturn(product);

        // Call the method to test
        Product result = productService.getProduct(1);

        // Assert the result
        assertEquals(1, result.getId());
        assertEquals("Product 1", result.getTitle());
        assertEquals(4.2, result.getRating(), 0.0);
    }

    // Add more test cases for the other methods in ProductService

}

