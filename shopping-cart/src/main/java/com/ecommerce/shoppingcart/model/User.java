package com.ecommerce.shoppingcart.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private Double height;
    private Double weight;
    private String eyeColor;
    private UserHairCharacteristics hair;
    private String domain;
    private String ip;
    private UserAddress address;
    private String macAddress;
    private String university;
    private UserBankDetails bank;
    private UserCompanyInfo company;
    private String ein;
    private String ssn;
    private String userAgent;
}
