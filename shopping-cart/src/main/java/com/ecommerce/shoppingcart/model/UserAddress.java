package com.ecommerce.shoppingcart.model;

import lombok.Data;

@Data
public class UserAddress {
    private String address;
    private String city;
    private UserAddressCoordinates coordinates;
    private String postalCode;
    private String state;
}