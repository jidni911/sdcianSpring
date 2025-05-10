package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.LoginRequest;
import com.jidnivai.sdcian.sdcian.dto.ResetPasswordDto;
import com.jidnivai.sdcian.sdcian.dto.UserSignupDto;
import jakarta.validation.Valid;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.AuthServiceInt;
import com.jidnivai.sdcian.sdcian.dto.EmailVerificationDto;
import com.jidnivai.sdcian.sdcian.payload.response.MessageResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServiceInt authServiceInt;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return authServiceInt.login(loginRequest);
        } catch (Exception e) {
            System.out.println("AuthController: " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse("Error logging in"));
        }
    }

    @PostMapping("/signup")
    public Boolean registerUser(@Valid @RequestBody UserSignupDto signUpRequest,
            @RequestParam(required = false) MultipartFile profilePicture) {
        try {
            return authServiceInt.signup(signUpRequest, profilePicture);
        } catch (Exception e) {
            System.out.println("AuthController: " + e.getMessage());
            return false;
        }
    }

    @PostMapping("/verifyEmail")
    public Boolean verifyEmail(@RequestBody EmailVerificationDto emailVerificationDto) {

        return authServiceInt.verifyEmail(emailVerificationDto);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        try {
            return authServiceInt.signout();
        } catch (Exception e) {
            System.out.println("AuthController: " + e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse("Error logging out"));
        }
    }


    @GetMapping("/forgotPassword")
    public String forgotPassword(@RequestParam String username) {
        return authServiceInt.forgotPassword(username);
    }

    @PostMapping("/resetPassword")
    public Boolean resetPassword(@RequestBody ResetPasswordDto value) {
        return authServiceInt.resetPassword(value);
    }

    @GetMapping("/checkUsernameAvailability")
    public Boolean checkUsernameAvailability(@RequestParam String username) {
        return authServiceInt.checkUsernameAvailability(username);
    }

    @GetMapping("/checkEmailAvailability")
    public Boolean checkEmailAvailability(@RequestParam String email) {
        return authServiceInt.checkEmailAvailability(email);
    }

    @GetMapping("/echo")
    public ResponseEntity<Boolean> echo() {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/getUserByIdentifier")
    public User getUserByIdentifier(@RequestParam String identifier) {
        try {
            return authServiceInt.getUserByIdentifier(identifier);
        } catch (Exception e) {
            return null;
        }
    }

}
