package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.request.*;
import art.sales.ArtsalesManagement.dao.response.CreateOrderResponse;
import art.sales.ArtsalesManagement.dao.response.UpdateUserResponse;
import art.sales.ArtsalesManagement.dao.response.UserLoginResponse;
import art.sales.ArtsalesManagement.dto.model.Art;
import art.sales.ArtsalesManagement.dto.model.Order;
import art.sales.ArtsalesManagement.dto.model.Role;
import art.sales.ArtsalesManagement.dto.model.User;
import art.sales.ArtsalesManagement.dto.model.enumPackage.RoleType;
import art.sales.ArtsalesManagement.dto.repository.UserRepository;
import art.sales.ArtsalesManagement.exception.UserCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices, UserDetailsService {
    private final UserRepository userRepository;
    private final OrderService orderService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(RegisterUserRequest registerUserRequest) {
        Optional<User> foundUser = userRepository.findUserByEmail(registerUserRequest.getEmail());
        if (foundUser.isPresent()) {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(registerUserRequest.getEmail()));
        } else {
            User registered = User.builder()
                    .address(registerUserRequest.getAddress())
                    .email(registerUserRequest.getEmail())
                    .firstName(registerUserRequest.getFirstName())
                    .lastName(registerUserRequest.getLastName())
                    .phoneNo(registerUserRequest.getPhoneNo())
                    .password(bCryptPasswordEncoder.encode(registerUserRequest.getPassword()))
                    .roleHashSet(new HashSet<>())
                    .build();
            registered.getRoleHashSet().add(new Role(RoleType.ROLE_USER));
            return userRepository.save(registered);
        }

    }

    @Override
    public long size() {
        return userRepository.count();
    }

    @Override
    public String deleteAllUser() {
        userRepository.deleteAll();
        return "User successfully deleted";

    }

    @Override
    public User findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(id));
        }

    }

    @Override
    public Page<User> findAllUser(FindAllUserRequest findAllUser) {
        Pageable pageable = PageRequest.of(findAllUser.getPages()-1, findAllUser.getNumberOfPages());
        return userRepository.findAll(pageable);

    }

    @Override
    public String deleteUserById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()){
            userRepository.deleteById(id);
            return "User successfully deleted";
        }
        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(id));
        }
    }

    @Override
    public UpdateUserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfile) {
        Optional<User> foundUser = userRepository.findById(updateUserProfile.getId());
        if (foundUser.isEmpty()) {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(updateUserProfile.getId()));
        } else {
            if (updateUserProfile.getAddress() != null) {
                foundUser.get().setAddress(updateUserProfile.getAddress());
            }
            if (updateUserProfile.getEmail() != null) {
                foundUser.get().setEmail(updateUserProfile.getEmail());
            }
            if (updateUserProfile.getFirstName() != null) {
                foundUser.get().setFirstName(updateUserProfile.getFirstName());
            }
            if (updateUserProfile.getLastName() != null) {
                foundUser.get().setLastName(updateUserProfile.getLastName());
            }
            if (updateUserProfile.getPassword() != null) {
                foundUser.get().setPassword(updateUserProfile.getPassword());
            }
            if (updateUserProfile.getPhoneNo() != null) {
                foundUser.get().setPhoneNo(updateUserProfile.getPhoneNo());
            }
            userRepository.save(foundUser.get());

        }
        return UpdateUserResponse.builder()
                .userId(foundUser.get().getId())
                .email("User with " + foundUser.get().getEmail() + " successfully updated")
                .build();
    }
    @Override
    public User findUserByEmail(String email) {
        Optional<User> foundUser = userRepository.findUserByEmail(email);
        if(foundUser.isPresent()){
            return foundUser.get();
        }
        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(email));
        }
    }


    @Override
    public CreateOrderResponse createArtOrder(CreateOrderRequest createOrderRequest) {
    Order savedOrder =    orderService.saveArtOrder(createOrderRequest);
     Optional<User> foundUser =   userRepository.findById(createOrderRequest.getId());
     if(foundUser.isPresent()){
         foundUser.get().getOrders().add(savedOrder);
         userRepository.save(foundUser.get());

     }
     else {
         throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(createOrderRequest.getId()));
     }
        return CreateOrderResponse.builder()
                .message("")
                .orderId(savedOrder.getId())
                .build();
    }





    @Override
    public long totalNoOfOrders() {
        return orderService.size();
    }
    @Override
    public void deleteAllOrders() {
        orderService.deleteAllOrder();
    }

    @Override
    public Order findOrderById(FindOrderByIdRequest findOrderByIdRequest) {
    Optional<User> foundUser = userRepository.findById(findOrderByIdRequest.getUserId());;
    if(foundUser.isPresent() ){
        return orderService.findById(findOrderByIdRequest.getOrderId());
    }
    else {
        throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(findOrderByIdRequest.getUserId()));

    }

    }

    @Override
    public Page<Order> findAllOrder(FindAllOrderRequest findAllOrderRequest) {
     Optional<User> foundUser =   userRepository.findById(findAllOrderRequest.getUserId());
     if(foundUser.isPresent()){
         return orderService.findAllOrders(findAllOrderRequest);
     }
     else {
         throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(findAllOrderRequest.getUserId()));
     }

    }

    @Override
    public UserLoginResponse login(UserLoginRequestModel userLoginRequestModel) {
        var user = userRepository.findUserByEmail(userLoginRequestModel.getEmail());
        if(user.isPresent() && user.get().getPassword().equals(userLoginRequestModel.getPassword()));
        return   buildSuccessfulLoginResponse(user.get());

    }
    private UserLoginResponse buildSuccessfulLoginResponse(User user) {
        return UserLoginResponse.builder()
                .code(200)
                .message("Login successful")
                .build();

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElse(null);
        if(user!= null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getRoleHashSet()));
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roleHashSet) {
        return roleHashSet.stream().map(role -> new SimpleGrantedAuthority(role.getRoleType().name())).collect(Collectors.toSet());
    }

//    @Override
//    public String deleteOrderById(DeleteOrderRequest deleteOrderRequest) {
//     Optional<User> foundUser =   userRepository.findById(deleteOrderRequest.getOrderId());
//     if(foundUser.isPresent()){
//         orderService.deleteOrderById(deleteOrderRequest.getOrderId());
//         return "Order with " + deleteOrderRequest.getOrderId() + "successfully deleted";
//     }
//     else {
//         throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(deleteOrderRequest.getUserId()));
//     }
//    }


}
