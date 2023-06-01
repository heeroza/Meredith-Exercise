package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.model.User;
import com.ecommerce.shoppingcart.model.contract.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserServiceImplUnitTest {

    @Mock
    private RestTemplate restTemplate;

    private UserService<User> userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(restTemplate);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1, "John");
        User user2 = new User(2, "Alice");
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(restTemplate.getForObject(anyString(), eq(User[].class))).thenReturn(expectedUsers.toArray(new User[0]));

        List<User> actualUsers = userService.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testGetUser() {
        User expectedUser = new User(1, "John");

        when(restTemplate.getForObject(anyString(), eq(User.class))).thenReturn(expectedUser);

        User actualUser = userService.getUser(1);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testSearchUsers() {
        User user1 = new User(1, "John");
        User user2 = new User(2, "Alice");
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(restTemplate.getForObject(anyString(), eq(User[].class))).thenReturn(expectedUsers.toArray(new User[0]));

        List<User> actualUsers = userService.searchUsers("John");

        assertEquals(expectedUsers, actualUsers);
    }
}
