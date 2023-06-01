package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.model.User;
import com.ecommerce.shoppingcart.model.contract.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService<User> {

    private static final String API_BASE_URL = "https://dummyjson.com";

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        String url = API_BASE_URL + "/users";
        return Arrays.asList(restTemplate.getForObject(url, User[].class));
    }

    @Override
    public User getUser(Integer userId) {
        String url = API_BASE_URL + "/users/" + userId;
        return restTemplate.getForObject(url, User.class);
    }

    @Override
    public List<User> searchUsers(String query) {
        String url = API_BASE_URL + "/users/search?q=" + query;
        return Arrays.asList(restTemplate.getForObject(url, User[].class));
    }
}
