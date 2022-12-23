package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.request.FindAllUserRequest;
import art.sales.ArtsalesManagement.dao.request.RegisterUserRequest;
import art.sales.ArtsalesManagement.dao.request.UpdateUserProfileRequest;
import art.sales.ArtsalesManagement.dao.response.UpdateUserResponse;
import art.sales.ArtsalesManagement.dto.model.User;
import org.springframework.data.domain.Page;

public interface  UserServices {
    User registerUser(RegisterUserRequest registerUserRequest);

    long size();

    void deleteAllUser();

    User findById(Long id);

    Page<User> findAllUser(FindAllUserRequest findAllUser);

    void deleteUserById(Long id);

    UpdateUserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfile);
}
