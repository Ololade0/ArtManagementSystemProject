package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.request.FindAllUserRequest;
import art.sales.ArtsalesManagement.dao.request.UpdateUserProfileRequest;
import art.sales.ArtsalesManagement.dao.response.UpdateUserResponse;
import art.sales.ArtsalesManagement.dto.model.User;
import art.sales.ArtsalesManagement.dto.model.enumPackage.RoleType;
import art.sales.ArtsalesManagement.dto.repository.UserRepository;
import art.sales.ArtsalesManagement.exception.UserCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
    private final UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User registered = User.builder()
                .address("ikeja")
                .email("adesuyiololade@gmail.com")
                .firstName("Ololade")
                .lastName("Demilade")
                .phoneNo("08109093828")
                .password("12345")
                .build();
//        user.getRoles().add(new Role(RoleType.ROLE_USER));
       return userRepository.save(registered);
    }

    @Override
    public long size() {
        return userRepository.count();
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();

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
    public void deleteUserById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()){
            userRepository.deleteById(id);
        }
        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(id));
        }
    }

    @Override
    public UpdateUserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfile) {
        Optional<User> foundUser = userRepository.findById(updateUserProfile.getId());
        if(foundUser.isEmpty()){
            throw new UserCannotBeFoundException(UserCannotBeFoundException.UserCannotBeFoundException(updateUserProfile.getId()));
        }
        else {
            if(updateUserProfile.getAddress()!= null){
                foundUser.get().setAddress(updateUserProfile.getAddress());
            }
            if(updateUserProfile.getEmail()!= null){
                foundUser.get().setEmail(updateUserProfile.getEmail());
            }
            if(updateUserProfile.getFirstName()!= null){
                foundUser.get().setFirstName(updateUserProfile.getFirstName());
            }
            if(updateUserProfile.getLastName()!= null){
                foundUser.get().setLastName(updateUserProfile.getLastName());
            }
            if(updateUserProfile.getPassword()!= null){
                foundUser.get().setPassword(updateUserProfile.getPassword());
            }
            if(updateUserProfile.getPhoneNo()!= null){
                foundUser.get().setPhoneNo(updateUserProfile.getPhoneNo());
            }
            userRepository.save(foundUser.get());

        }
        return UpdateUserResponse.builder()
                .userId(foundUser.get().getId())
                .email("User with " + foundUser.get().getEmail() + " successfully updated")
               .build();
    }
}
