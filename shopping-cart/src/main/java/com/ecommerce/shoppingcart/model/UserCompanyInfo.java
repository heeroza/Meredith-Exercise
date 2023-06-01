package com.ecommerce.shoppingcart.model;


import lombok.Data;

@Data
public class UserCompanyInfo {
    private UserAddress address;
    private String department;
    private String name;
    private String title;
}
