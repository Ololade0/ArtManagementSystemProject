package art.sales.ArtsalesManagement.controller;

import art.sales.ArtsalesManagement.dao.request.RegisterUserRequest;
import art.sales.ArtsalesManagement.dto.model.User;
import art.sales.ArtsalesManagement.security.jwt.TokenProvider;
import art.sales.ArtsalesManagement.service.UserServices;
import com.mashape.unirest.http.exceptions.UnirestException;;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserLoginRequestModel loginRequest) throws UserCannotBeFoundExcepttion {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
//                        loginRequest.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println("3");
//        final String token = tokenProvider.generateJWTToken(authentication);
//        UserEntity user = userService.findByEmail(loginRequest.getEmail());
//        return new ResponseEntity<>(new AuthToken(token, user.getId()), HttpStatus.OK);
//    }


}
