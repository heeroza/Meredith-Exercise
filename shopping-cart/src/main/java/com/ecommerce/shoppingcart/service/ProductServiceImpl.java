package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.model.ProductsApiResponse;
import com.ecommerce.shoppingcart.model.contract.ProductService;
import com.ecommerce.shoppingcart.model.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService<Product, String> {

    private static final String API_BASE_URL = "https://dummyjson.com";

    private final RestTemplate restTemplate;

    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        String url = API_BASE_URL + "/products";
        ProductsApiResponse productsApiResponse = restTemplate.getForObject(url, ProductsApiResponse.class);
        return productsApiResponse.getProducts();
    }

    @Override
    public List<Product> getAllProducts(int limit, int skip, String... fields) {
        String url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL + "/products")
                .queryParam("limit", limit)
                .queryParam("skip", skip)
                .queryParam("select", fields)
                .toUriString();
        return Arrays.asList(restTemplate.getForObject(url, Product[].class));
    }

    @Override
    public Product getProduct(Integer productId) {
        log.info("Getting product information for product with id: " + productId);
        String url = API_BASE_URL + "/products/" + productId;
        return restTemplate.getForObject(url, Product.class);
    }

    @Override
    public List<Product> searchProducts(String query) {

        String url = API_BASE_URL + "/products/search?q=" + query;
        return Arrays.asList(restTemplate.getForObject(url, Product[].class));
    }

    @Override
    public List<String> getCategories() {
        String url = API_BASE_URL + "/products/categories";
        return Arrays.asList(restTemplate.getForObject(url, String[].class));
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        String url = API_BASE_URL + "/products/category/" + categoryName;
        return Arrays.asList(restTemplate.getForObject(url, Product[].class));
    }
}
