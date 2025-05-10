package com.jidnivai.sdcian.sdcian.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.EmailVerificationDto;
import com.jidnivai.sdcian.sdcian.dto.JwtResponse;
import com.jidnivai.sdcian.sdcian.dto.LoginRequest;
import com.jidnivai.sdcian.sdcian.dto.ResetPasswordDto;
import com.jidnivai.sdcian.sdcian.dto.UserSignupDto;
import com.jidnivai.sdcian.sdcian.entity.PreUser;
import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.enums.Gender;
import com.jidnivai.sdcian.sdcian.interfaces.AuthServiceInt;
import com.jidnivai.sdcian.sdcian.payload.response.MessageResponse;
import com.jidnivai.sdcian.sdcian.repository.PreUserRepository;
import com.jidnivai.sdcian.sdcian.repository.RoleRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;
import com.jidnivai.sdcian.sdcian.security.jwt.JwtUtils;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

import jakarta.mail.MessagingException;

@Service
public class AuthService implements AuthServiceInt {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PreUserRepository preUserRepository;

    @Override
    public Boolean resetPassword(ResetPasswordDto resetPasswordDto) {
        User user = userRepository.findByUsername(resetPasswordDto.getUsername()).orElse(null);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }
        if (user.getEmailOTP() == null || user.getEmailOtpExpiryTime().isBefore(LocalDateTime.now())) {
            System.out.println("OTP expired");
            return false;
        }
        if (!user.getEmailOTP().equals(resetPasswordDto.getOtp())) {
            System.out.println("Invalid OTP:" + resetPasswordDto.getOtp() + " " + user.getEmailOTP());
            return false;
        }
        user.setPassword(encoder.encode(resetPasswordDto.getPassword()));
        user.setEmailOTP(null);
        user.setEmailOtpExpiryTime(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public String forgotPassword(String username) {

        // get email,
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return "username not found";
        }

        // generate otp,
        String otp = generateEmailOTP();
        // save otp in db
        user.setEmailOTP(otp);
        user.setEmailOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        // send otp to email
        try {
            emailService.sendOtp(user.getEmail(), otp);
        } catch (MessagingException e) {
            System.out.println("AuthService.forgotPassword(): " + e.getMessage());
            return "email not sent";
        }
        // reset password
        return "email sent";
    }

    @Override
    public Boolean checkUsernameAvailability(String username) {
        return !userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public Boolean checkEmailAvailability(String email) {
        return !userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public User getUserByIdentifier(String identifier) {
        User realUser = userRepository.findByUsername(identifier).orElse(null);
        if (realUser == null) {
            realUser = userRepository.findByEmail(identifier).orElse(null);
        }
        if (realUser == null) {
            return null;
        }
        // if (realUser.getEmailVerified() == false) {
        // return null;
        // }

        User user = new User();
        user.setUsername(realUser.getUsername());
        user.setFullName(realUser.getFullName());
        user.setProfilePicture(realUser.getProfilePicture());
        user.setProfileMusic(realUser.getProfileMusic());
        return user;

    }

    private String generateEmailOTP() {

        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userDetails.getUser();

        ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(userDetails);

        String token = jwtUtil.generateJwtToken(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new JwtResponse(user, token));
    }

    @Override
    public ResponseEntity<?> signout() {
        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @Override
    public Boolean verifyEmail(EmailVerificationDto emailVerificationDto) {
        PreUser user = preUserRepository.findByEmail(emailVerificationDto.getEmail()).orElse(null);
        if (user == null) {
            return false;
        } else {
            // TODO imply TTL
            if (user.getEmailOTP().equals(emailVerificationDto.getOtp())) {
                user.setEmailVerified(true);
                User newUser = new User();
                BeanUtils.copyProperties(user, newUser);
                newUser.setId(null);

                Set<Role> roles = new HashSet<>();
                Role role = new Role();
                role.setName("ROLE_USER");
                roles.add(roleRepository.save(role));

                newUser.setRoles(roles);
                userRepository.save(newUser);
                preUserRepository.deleteById(user.getId());
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Boolean signup(UserSignupDto signUpRequest, MultipartFile profilePicture) {
        if (!checkUsernameAvailability(signUpRequest.getUsername())) {
            System.out.println("Username not available :" + signUpRequest.getUsername());
            return false;
        }

        if (!checkEmailAvailability(signUpRequest.getEmail())) {
            System.out.println("Email not available :" + signUpRequest.getEmail());
            return false;
        }

        if (!signUpRequest.getPassword().equals(signUpRequest.getRetypePassword())) {
            System.out.println("Passwords do not match");
            return false;
        }
        PreUser preUser = preUserRepository.findByEmail(signUpRequest.getEmail()).orElse(null);
        if (preUser != null) {
            if (preUser.getEmailOtpExpiryTime().isBefore(LocalDateTime.now())) {
                preUserRepository.deleteById(preUser.getId());
            } else {
                return false;
            }
        }

        PreUser preUser1 = preUserRepository.findByUsername(signUpRequest.getUsername()).orElse(null);
        if (preUser1 != null) {
            if (preUser1.getEmailOtpExpiryTime().isBefore(LocalDateTime.now())) {
                preUserRepository.deleteById(preUser1.getId());
            } else {
                return false;
            }
        }

        PreUser user = new PreUser();
        user.setFullName(signUpRequest.getFullName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setGender(Gender.valueOf(signUpRequest.getGender().toUpperCase()));
        user.setDob(signUpRequest.getDob());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setAddress(signUpRequest.getAddress());
        // TODO - Handle profile picture upload separately

        String emailOtp = generateEmailOTP();
        user.setEmailOTP(emailOtp);
        user.setEmailOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
        try {
            emailService.sendOtp(user.getEmail(), emailOtp);
        } catch (MessagingException e) {
            System.out.println("authController: " + e.getMessage());
            return false;
        }
        user = preUserRepository.save(user);

        return true;
    }

}
