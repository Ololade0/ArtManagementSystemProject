package art.sales.ArtsalesManagement.config;

import art.sales.ArtsalesManagement.dto.model.User;
import art.sales.ArtsalesManagement.dto.model.enumPackage.RoleType;
import art.sales.ArtsalesManagement.dto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.findUserByEmail("adesuyi@gmail.com").isEmpty()){
            User user = new User("Ololade", "Ola","ololade@gmail.com", passwordEncoder.encode("12345"), "12345", "Sabo", RoleType.ROLE_USER);
            userRepository.save(user);
        }
    }
}
