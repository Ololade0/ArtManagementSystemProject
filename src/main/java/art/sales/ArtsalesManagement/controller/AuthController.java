package art.sales.ArtsalesManagement.controller;
import art.sales.ArtsalesManagement.dao.request.*;
import art.sales.ArtsalesManagement.dao.response.CreateOrderResponse;
import art.sales.ArtsalesManagement.dao.response.UpdateUserResponse;
import art.sales.ArtsalesManagement.dto.model.AuthToken;
import art.sales.ArtsalesManagement.dto.model.Order;
import art.sales.ArtsalesManagement.dto.model.User;
import art.sales.ArtsalesManagement.exception.OrderCannotBeFoundException;
import art.sales.ArtsalesManagement.exception.UserCannotBeFoundException;
import art.sales.ArtsalesManagement.security.jwt.TokenProvider;
import art.sales.ArtsalesManagement.service.UserServices;
import com.mashape.unirest.http.exceptions.UnirestException;;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
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
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = tokenProvider.generateJWTToken(authentication);
        User user = userService.findUserByEmail(loginRequest.getEmail());
        return new ResponseEntity<>(new AuthToken(token, user.getId()), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> findUserById(@PathVariable Long userId) {
        try {
            User foundUser = userService.findById(userId);
            return new ResponseEntity<>(foundUser, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        try {
            User foundUser = userService.findUserByEmail(email);
            return new ResponseEntity<>(foundUser, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<?> findUserAllUser(@RequestBody FindAllUserRequest findAllUserRequest) {
        try {
            Page<User> foundUsers = userService.findAllUser(findAllUserRequest);
            return new ResponseEntity<>(foundUsers, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllUsers() {
        try {
            String deletedUsers = userService.deleteAllUser();
            return new ResponseEntity<>(deletedUsers, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        try {
            String deletedUser = userService.deleteUserById(userId);
            return new ResponseEntity<>(deletedUser, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping()
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateUserProfileRequest updateUserProfileRequest) {
        try {
            UpdateUserResponse updateUserProfile = userService.updateUserProfile(updateUserProfileRequest);
            return new ResponseEntity<>(updateUserProfile, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/order")
    public ResponseEntity<?> createNote(@RequestBody CreateOrderRequest createOrderRequest) {
        try {
            CreateOrderResponse createNoteResponse = userService.createArtOrder(createOrderRequest);
            return new ResponseEntity<>(createNoteResponse, HttpStatus.CREATED);
        } catch (UserCannotBeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<?> findNoteById(@RequestBody FindOrderByIdRequest findNoteByIdRequest) throws OrderCannotBeFoundException {
        Order savedOrder = userService.findOrderById(findNoteByIdRequest);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<?> findAllNote(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "2") int limit) {
//        List<Note> savedNote = userService.findAllOrder(page, limit);
//        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
//    }


}
