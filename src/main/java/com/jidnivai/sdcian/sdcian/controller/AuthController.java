package com.jidnivai.sdcian.sdcian.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.security.jwt.JwtUtils;
import com.jidnivai.sdcian.sdcian.dto.LoginRequest;
import com.jidnivai.sdcian.sdcian.dto.UserSignupDto;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;
import com.jidnivai.sdcian.sdcian.repository.RoleRepository;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;
import com.jidnivai.sdcian.sdcian.entity.Role;

import jakarta.validation.Valid;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.enums.Gender;
import com.jidnivai.sdcian.sdcian.interfaces.AuthServiceInt;
import com.jidnivai.sdcian.sdcian.dto.JwtResponse;
import com.jidnivai.sdcian.sdcian.payload.response.MessageResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthServiceInt authServiceInt;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = new User();

        // List<String> roles = userDetails.getAuthorities().stream()
        // .map(GrantedAuthority::getAuthority)
        // .toList();
        user.setId(userDetails.getId());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setRoles(userDetails.getRoles());
        user.setFullName(userDetails.getFullName());
        user.setGender(userDetails.getGender());
        user.setDob(userDetails.getDob());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setAddress(userDetails.getAddress());

        ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(userDetails);

        String token = jwtUtil.generateJwtToken(userDetails);

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new JwtResponse(user, token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
        @Valid @RequestBody UserSignupDto signUpRequest,
        @RequestParam(required = false) MultipartFile profilePicture
        ) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
;

        if(!signUpRequest.getPassword().equals(signUpRequest.getRetypePassword())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Passwords do not match!"));
        }

        User user = new User();

        user.setFullName(signUpRequest.getFullName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setGender(Gender.valueOf(signUpRequest.getGender().toUpperCase()));
        user.setDob(signUpRequest.getDob());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setAddress(signUpRequest.getAddress());
        //TODO - Handle profile picture upload separately

        // Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(roleRepository.save(role));

        user = userRepository.save(user);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    // forgot password TODO rework

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return authServiceInt.forgotPassword("email");
    }

     @GetMapping("/checkUsernameAvailability")
    public Boolean checkUsernameAvailability(@RequestParam String username){
        return authServiceInt.checkUsernameAvailability(username);
    }

    
    @GetMapping("/checkEmailAvailability")
    public Boolean checkEmailAvailability(@RequestParam String email){
        return authServiceInt.checkEmailAvailability(email);
    }

    @GetMapping("/test")
    public UserDetailsImpl testAuth(@AuthenticationPrincipal UserDetailsImpl user) { 
        System.out.println(user);
        return user;
    }

    @GetMapping("/allUserNames")
    public List<String> getMethodName() {
        return authServiceInt.getAllUserNames();
    }

    @GetMapping("/echo")
    public ResponseEntity<Boolean> echo() {
        return ResponseEntity.ok(true);
    }
    


}
