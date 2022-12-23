package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.request.*;
import art.sales.ArtsalesManagement.dao.response.UpdateUserResponse;
import art.sales.ArtsalesManagement.dto.model.Role;
import art.sales.ArtsalesManagement.dto.model.User;
import art.sales.ArtsalesManagement.dto.model.enumPackage.PaymentType;
import art.sales.ArtsalesManagement.dto.model.enumPackage.RoleType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class UserServiceImplTest {
    User registeredUser;
    @Autowired
    private  UserServices userServices;

    @BeforeEach
    void setUp() {
        RegisterUserRequest registerUserRequest = RegisterUserRequest.builder()
                      .address("ikeja")
                .email("adesuyiololade@gmail.com")
                .firstName("Ololade")
                .lastName("Demilade")
                .phoneNo("08109093828")
                .password("12345")
               .build();
        registeredUser =   userServices.registerUser(registerUserRequest);
    }

    @AfterEach
    void tearDown() {
        userServices.deleteAllUser();
    }
    @Test
    void userCanBeRegister(){
        RegisterUserRequest registerUserRequest = RegisterUserRequest.builder()
                .address("ikeja")
                .email("adesuyiololad@gmail.com")
                .firstName("Ololade")
                .lastName("Demilade")
                .phoneNo("08109093828")
                .password("12345")
                .build();
//        registeredUser.getRoleHashSet().add(new Role(RoleType.ROLE_USER));
        registeredUser =   userServices.registerUser(registerUserRequest);
//        registeredUserR.getRoleHashSet().add(new Role(RoleType.ROLE_USER));

        assertThat(registeredUser.getId()).isNotNull();
        assertEquals(2, userServices.size());

    }

    @Test
    void userCanBeFindById(){
    User foundUser =  userServices.findById(registeredUser.getId());
    assertThat(foundUser.getId()).isEqualTo(registeredUser.getId());
    assertThat(foundUser.getId()).isNotNull();
    }
    @Test
    void userCanBeFindUserByEmail() {
     User foundUser =   userServices.findUserByEmail(registeredUser.getEmail());
        assertEquals("adesuyiololade@gmail.com", foundUser.getEmail());


    }


    @Test
    void userCanBeFindAllUser(){

        FindAllUserRequest findAllUser = FindAllUserRequest.builder()
                .numberOfPages(1)
                .pages(2)
                .id(registeredUser.getId())
                .build();
     Page<User> foundUser =   userServices.findAllUser(findAllUser);
        assertEquals(1, foundUser.getTotalElements());
        assertThat(foundUser.getTotalElements()).isNotNull();
    }
    @Test
    void userCanBeDeleteUserById(){
        userServices.deleteUserById(registeredUser.getId());
        assertEquals(0, userServices.size());
    }
    @Test
    void userCanBeDeleteAllUser(){
        userServices.deleteAllUser();
        assertEquals(0, userServices.size());
    }
@Test
    void userCanBeUpdated(){
        UpdateUserProfileRequest updateUserProfile = UpdateUserProfileRequest.builder()
                .id(registeredUser.getId())
                .address("Lekki")
                .email("lolade@gmail.com")
                .firstName("Tosin")
                .lastName("EUnice")
                .phoneNo("09131807593")
                .password("1234567")
                .build();
    UpdateUserResponse foundUser =     userServices.updateUserProfile(updateUserProfile);
    assertEquals("User with lolade@gmail.com successfully updated", foundUser.getEmail());
    assertThat(foundUser.getUserId()).isNotNull();
    }
    @Test
    void userCanOrderArt(){
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .ordered_at(LocalDateTime.now())
                .paymentTime(LocalDateTime.now())
                .address("Nigeria")
                .paymentDescription("Orders Description")
                .paymentType(PaymentType.DEBIT_CARD)
                .email("adesuyiololade@gmail.com")
                .build();
        userServices.createArtOrder(createOrderRequest);

    }

}