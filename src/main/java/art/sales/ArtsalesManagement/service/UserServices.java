package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.request.*;
import art.sales.ArtsalesManagement.dao.response.CreateOrderResponse;
import art.sales.ArtsalesManagement.dao.response.UpdateUserResponse;
import art.sales.ArtsalesManagement.dto.model.Order;
import art.sales.ArtsalesManagement.dto.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface  UserServices {
    User registerUser(RegisterUserRequest registerUserRequest);

    long size();

    void deleteAllUser();

    User findById(Long id);

    Page<User> findAllUser(FindAllUserRequest findAllUser);

    void deleteUserById(Long id);

    UpdateUserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfile);

    CreateOrderResponse createArtOrder(CreateOrderRequest createOrderRequest);

    User findUserByEmail(String email);

    long totalNoOfOrders();

    void deleteAllOrders();


    Order findOrderById(FindOrderByIdRequest findOrderByIdRequest);

    Page<Order> findAllOrder(FindAllOrderRequest findAllOrderRequest);
}
