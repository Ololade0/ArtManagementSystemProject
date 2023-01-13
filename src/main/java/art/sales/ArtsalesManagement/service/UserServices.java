package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.model.Order;
import art.sales.ArtsalesManagement.dao.model.User;
import art.sales.ArtsalesManagement.dto.request.*;
import art.sales.ArtsalesManagement.dto.response.CreateOrderResponse;
import art.sales.ArtsalesManagement.dto.response.UpdateUserResponse;
import art.sales.ArtsalesManagement.dto.response.UserLoginResponse;
import org.springframework.data.domain.Page;

public interface  UserServices {
    User registerUser(RegisterUserRequest registerUserRequest);

    long size();

    String deleteAllUser();

    User findById(Long id);

    Page<User> findAllUser(FindAllUserRequest findAllUser);

    String deleteUserById(Long id);

    UpdateUserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfile);

    CreateOrderResponse createArtOrder(CreateOrderRequest createOrderRequest);

    User findUserByEmail(String email);

    long totalNoOfOrders();

    void deleteAllOrders();


    Order findOrderById(FindOrderByIdRequest findOrderByIdRequest);

    Page<Order> findAllOrder(FindAllOrderRequest findAllOrderRequest);

    UserLoginResponse login(UserLoginRequestModel userLoginRequestModel);

//    String deleteOrderById(DeleteOrderRequest deleteOrderRequest);
}
