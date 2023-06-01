package com.ecommerce.shoppingcart;

import com.ecommerce.shoppingcart.model.Cart;
import com.ecommerce.shoppingcart.model.CartItem;
import com.ecommerce.shoppingcart.model.Product;
import com.ecommerce.shoppingcart.service.CartServiceImpl;
import com.ecommerce.shoppingcart.service.ProductServiceImpl;
import com.ecommerce.shoppingcart.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MartAppFeaturesUnitTest {

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private CartServiceImpl cartService;

    @Mock
    private UserServiceImpl userService;

    @Spy
    @InjectMocks
    private MartAppFeatures martAppFeatures;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testGetProductTitlesByWorseRating() {
        //prepare
        double ratingThreshold = 3.5;
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product 1", "Description 1", 20.0, 10.0, 4.69, 94, "Apple", "smartPhones", "https://dummyimage.com/1.jpg", Arrays.asList("https://i.dummyjson.com/data/products/1/1.jpg,https://i.dummyjson.com/data/products/1/2.jpg")));
        products.add(new Product(2, "Product 2", "Description 2", 30.0, 5.0, 4.44, 34, "Samsung", "smartPhones", "https://dummyimage.com/2.jpg", Arrays.asList("https://i.dummyjson.com/data/products/2/1.jpg", "https://i.dummyjson.com/data/products/2/2.jpg")));
        when(productService.getAllProducts()).thenReturn(products);

        //test
        martAppFeatures.getProductTitlesByWorseRating(ratingThreshold);

        //assert
        verify(productService, times(1)).getAllProducts();
    }

  @Test
    public void testGetCartWithHighestTotal() {
        List<Cart> carts = new ArrayList<>();
        CartItem item = new CartItem(59, "Spring and summershoes", 20.00, 3, 60.00, 8.71, 55.00);
        carts.add(new Cart(1, Arrays.asList(item), 2328.00, 1941.00, 97, 5, 10));

        when(cartService.getAllCarts()).thenReturn(carts);

        Cart highestTotalCart = martAppFeatures.getCartWithHighestTotal();

        assertEquals(1, highestTotalCart.getId());
        assertEquals(2328.00, highestTotalCart.getTotal());
    }

  /*  @Test
    public void testGetCartWithLowestTotal() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(1, 100.0));
        carts.add(new Cart(2, 150.0));
        carts.add(new Cart(3, 80.0));

        when(cartService.getAllCarts()).thenReturn(carts);

        Cart lowestTotalCart = martAppFeatures.getCartWithLowestTotal();

        assertEquals(3, lowestTotalCart.getId());
        assertEquals(80.0, lowestTotalCart.getTotal());
    }

   @Test
    public void testAddProductImagesToUserCart() {
        int userId = 1;
        List<Cart> userCarts = new ArrayList<>();
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(1, "Product 1", 10.0));
        cartItems.add(new CartItem(2, "Product 2", 20.0));
        userCarts.add(new Cart(userId, cartItems));

        List<Product> detailedProducts = new ArrayList<>();
        detailedProducts.add(new Product(1, "Product 1", 10.0, "https://example.com/image1.jpg"));
        detailedProducts.add(new Product(2, "Product 2", 20.0, "https://example.com/image2.jpg"));

        when(cartService.getUserCarts(userId)).thenReturn(userCarts);
        when(productService.getProduct(1)).thenReturn(detailedProducts.get(0));
        when(productService.getProduct(2)).thenReturn(detailedProducts.get(1));

        List<Product> enrichedProducts = martAppFeatures.addProductImagesToUserCart(userId);

        assertEquals(2, enrichedProducts.size());
        assertEquals("https://example.com/image1.jpg", enrichedProducts.get(0).getImages().get(0));
        assertEquals("https://example.com/image2.jpg", enrichedProducts.get(1).getImages().get(0));
    }*/
}
