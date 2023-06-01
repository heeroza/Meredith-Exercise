package com.ecommerce.shoppingcart;

import com.ecommerce.shoppingcart.model.Cart;
import com.ecommerce.shoppingcart.model.CartItem;
import com.ecommerce.shoppingcart.model.Product;
import com.ecommerce.shoppingcart.service.CartServiceImpl;
import com.ecommerce.shoppingcart.service.ProductServiceImpl;
import com.ecommerce.shoppingcart.service.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
@Log4j2
public class MartAppFeatures {

    private final ProductServiceImpl productService;
    private final CartServiceImpl cartService;
    private final UserServiceImpl userService;

    @Autowired
    public MartAppFeatures(ProductServiceImpl productService, CartServiceImpl cartService, UserServiceImpl userService) {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
    }

    /**
     * Prints the titles of all products that have a rating less than or equal to the provided criteria.
     *
     * @param rating The rating threshold.
     */
    public void getProductTitlesByWorseRating(double rating) {

        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            if (product.getRating() <= rating) {
                System.out.println(product.getTitle());
            }
        }
    }

    /**
     * Returns the cart with the highest total value.
     *
     * @returns The cart with the highest total value.
     */
    public Cart getCartWithHighestTotal() {
        List<Cart> carts = cartService.getAllCarts();
        return carts.stream()
                .max(Comparator.comparingDouble(Cart::getTotal))
                .orElseGet(() -> null);
    }

    /**
     * Returns the cart with the lowest total value.
     *
     * @returns The cart with the lowest total value.
     */
    public Cart getCartWithLowestTotal() {
        List<Cart> carts = cartService.getAllCarts();
        return carts.stream()
                .min(Comparator.comparingDouble(Cart::getTotal))
                .orElseGet(() -> null);
    }

    /**
     * Enriches the product information in a user's cart by adding product images.
     * The current product information in a cart has limited fields.
     * This method adds the `images` field for each product in a given user's cart.
     * Note: This method only applies to the first element from the `carts[]` JSON response.
     *
     * @param userId The ID of the user whose cart's product information will be enriched.
     * @returns A list of products with enriched information in the user's cart.
     */
    public List<Product> addProductImagesToUserCart(Integer userId) {
        List<Cart> userCarts = cartService.getUserCarts(userId);
        if (userCarts == null && userCarts.isEmpty()) {
            log.info("Cart is empty for user with id: "+ userId);
            return Collections.EMPTY_LIST;
        }

        Cart selectedCart = userCarts.get(0);
        List<Product> cartProductsEnrichedWithImages = new ArrayList<>();
        for (CartItem cartItem : selectedCart.getProducts()) {
            Product detailedProduct = productService.getProduct(cartItem.getId());
            if (detailedProduct.getImages() != null && !detailedProduct.getImages().isEmpty()) {
                cartItem.setImages(detailedProduct.getImages());
            }
            cartProductsEnrichedWithImages.add(cartItem);

        }
        return cartProductsEnrichedWithImages;
    }
}
