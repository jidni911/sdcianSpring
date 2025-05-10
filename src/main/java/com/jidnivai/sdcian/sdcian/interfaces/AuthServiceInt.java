package com.jidnivai.sdcian.sdcian.interfaces;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.EmailVerificationDto;
import com.jidnivai.sdcian.sdcian.dto.LoginRequest;
import com.jidnivai.sdcian.sdcian.dto.ResetPasswordDto;
import com.jidnivai.sdcian.sdcian.dto.UserSignupDto;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface AuthServiceInt {


    public String forgotPassword(String username);


    public Boolean checkUsernameAvailability(String username);

    public Boolean checkEmailAvailability(String email);

    public User getUserByIdentifier(String identifier);

    public Boolean verifyEmail(EmailVerificationDto emailVerificationDto);

    public ResponseEntity<?> login(LoginRequest loginRequest);

    public Boolean signup(UserSignupDto signUpRequest, MultipartFile profilePicture);

    public ResponseEntity<?> signout();


    public Boolean resetPassword(ResetPasswordDto resetPasswordDto);

    
}
