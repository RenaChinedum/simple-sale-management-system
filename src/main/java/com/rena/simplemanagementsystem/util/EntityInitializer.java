package com.rena.simplemanagementsystem.util;

import com.rena.simplemanagementsystem.Repository.UserRepository;
import com.rena.simplemanagementsystem.enums.Role;
import com.rena.simplemanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class EntityInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EntityInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
       User admin = new User();
       admin.setFirstName("Admin 1");
       admin.setLastName("Admin");
       admin.setEmail("admin1@gmail.com");
       admin.setRole(Role.ADMIN);
       admin.setPassword(passwordEncoder.encode("Admin1@maid"));
//       userRepository.save(admin);
    }
}
