package com.ecommerce.shoppingcart;

import com.ecommerce.shoppingcart.model.Cart;
import com.ecommerce.shoppingcart.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MartAppFeaturesIntegrationTest {

    @Autowired
    private MartAppFeatures martAppFeatures;

    @Test
    public void testGetProductTitlesByWorseRating() {
        double ratingThreshold = 4.0;
        martAppFeatures.getProductTitlesByWorseRating(ratingThreshold);
    }

    @Test
    public void testGetCartWithHighestTotal() {
        Cart cartWithHighestTotal = martAppFeatures.getCartWithHighestTotal();
    }

    @Test
    public void testGetCartWithLowestTotal() {
        Cart cartWithLowestTotal = martAppFeatures.getCartWithLowestTotal();
    }

    @Test
    public void testAddProductImagesToUserCart() {
        Integer userId = 1; // Replace with an actual user ID
        List<Product> productsEnrichedWithImages = martAppFeatures.addProductImagesToUserCart(userId);
        assertNotNull(productsEnrichedWithImages);
    }
}
