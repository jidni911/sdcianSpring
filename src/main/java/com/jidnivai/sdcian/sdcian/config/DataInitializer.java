package com.jidnivai.sdcian.sdcian.config;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jidnivai.sdcian.sdcian.controller.AuthController;
import com.jidnivai.sdcian.sdcian.dto.UserSignupDto;
import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.repository.RoleRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthController authController;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create roles if they don't exist
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            Role sellerRole = new Role();
            sellerRole.setName("ROLE_SELLER");
            roleRepository.save(sellerRole);

            
            Role devRole = new Role();
            devRole.setName("ROLE_DEV");
            roleRepository.save(devRole);

            System.out.println("Default roles created");
        }

        // Create default admin user if no users exist
        if (userRepository.count() == 0) {
            UserSignupDto admin = new UserSignupDto();
            admin.setFullName("Jidni Khan");
            admin.setUsername("jidnivai");
            admin.setEmail("gidni441@gmail.com");
            admin.setPassword("Onetwothree1@3"); // Note: hash password in real apps!
            admin.setRetypePassword("Onetwothree1@3"); // Note: hash password in real apps!
            admin.setGender("Male");
            admin.setDob(LocalDate.parse("1999-12-27"));
            admin.setPhoneNumber("01719987447");
            admin.setAddress("Keraniganj, Dhaka, Bangladesh");
            authController.registerUser(admin, null);
            User user = userRepository.findByUsername("jidnivai").orElse(null);
            user.setRoles(new HashSet<>(roleRepository.findAll()));
            userRepository.save(user);

            UserSignupDto demoUser = new UserSignupDto();
            demoUser.setFullName("Demo User");
            demoUser.setUsername("demoUser");
            demoUser.setEmail("demo@example.com");
            demoUser.setPassword("Demo@1234"); // Note: hash password in real apps!
            demoUser.setRetypePassword("Demo@1234"); // Note: hash password in real apps!
            demoUser.setGender("MALE");
            demoUser.setDob(LocalDate.parse("2000-01-01"));
            demoUser.setPhoneNumber("1234567890");
            demoUser.setAddress("123 Demo Street, Demo City");
            authController.registerUser(demoUser, null);

            System.out.println("Default God user and Demo User created");
        }
    }
}
