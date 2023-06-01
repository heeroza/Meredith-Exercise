package com.ecommerce.shoppingcart.model;


import lombok.Data;

@Data
public class UserBankDetails {
    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;
}
