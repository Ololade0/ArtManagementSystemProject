package art.sales.ArtsalesManagement.controller;

import art.sales.ArtsalesManagement.dao.model.AuthToken;
import art.sales.ArtsalesManagement.dao.model.Order;
import art.sales.ArtsalesManagement.dao.model.User;
import art.sales.ArtsalesManagement.dto.request.*;
import art.sales.ArtsalesManagement.dto.response.CreateOrderResponse;
import art.sales.ArtsalesManagement.dto.response.UpdateUserResponse;
import art.sales.ArtsalesManagement.exception.UserCannotBeFoundException;
import art.sales.ArtsalesManagement.security.jwt.TokenProvider;
import art.sales.ArtsalesManagement.service.UserServices;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserServices userService;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@NonNull @RequestBody RegisterUserRequest registerUserRequest) throws UnirestException {
        User user = userService.registerUser(registerUserRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestModel loginRequest) throws UserCannotBeFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword())
        );
        userService.login(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateJWTToken(authentication);
        User user = userService.findUserByEmail(loginRequest.getEmail());
        return new ResponseEntity<>(new AuthToken(token, user.getId()), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(FindAllUserRequest findAllUserRequest) {
        Page<User> user = userService.findAllUser(findAllUserRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
    return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> deleteAllUsers(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.CREATED);
    }


    @PutMapping("/updateuser")
    public ResponseEntity<?> updateUsers(UpdateUserProfileRequest updateUserProfile){
      UpdateUserResponse updatedUser = userService.updateUserProfile(updateUserProfile);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @PostMapping("/order")
    public ResponseEntity<?> OrderArt(@RequestBody CreateOrderRequest createOrderRequest){
        CreateOrderResponse order = userService.createArtOrder(createOrderRequest);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/orderId")
    public ResponseEntity<?> findOrderById(FindOrderByIdRequest findOrderByIdRequest){
        Order foundOrder = userService.findOrderById(findOrderByIdRequest);
        return new ResponseEntity<>(foundOrder, HttpStatus.CREATED);
    }

    @GetMapping("/findorders")
    public ResponseEntity<?> findAllOrder(FindAllOrderRequest findAllOrderRequest){
        Page<Order> foundOrder = userService.findAllOrder(findAllOrderRequest);
        return new ResponseEntity<>(foundOrder, HttpStatus.CREATED);
    }









}
