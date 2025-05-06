package com.jidnivai.sdcian.sdcian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.AuthServiceInt;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Service
public class AuthService implements AuthServiceInt {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public String forgotPassword(String email) {
        //TODO 
        return null;
    }

    @Override
    public String signup(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
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
    public List<String> getAllUserNames() {
        return userRepository.findAllUsernames();
    }

    @Override
    public User getUserByIdentifier(String identifier) {
        User realUser = userRepository.findByUsername(identifier).orElse(null);
        if (realUser == null) {
            realUser = userRepository.findByEmail(identifier).orElse(null);
        }
        if (realUser == null) {
            return null;
        } else {
            User user = new User();
            user.setUsername(realUser.getUsername());
            user.setFullName(realUser.getFullName());
            user.setProfilePicture(realUser.getProfilePicture());
            user.setProfileMusic(realUser.getProfileMusic());
            return user;
        }
    }
    
}

//     //TODO rework
//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public User login(String username, String password) {
//         User user = userRepository.findByUsername(username);
//         if (user != null && user.getPassword().equals(password)) {
//             return user;
//         }
//         return null;
//     }

//     @Override
//     public String forgotPassword(String email) {
//         User user = userRepository.findByEmail(email);
//         if (user != null) {
//             // Implement password reset logic here
//             return "Password reset instructions sent";
//         }
//         return "Email not found";
//     }

//     @Override
//     public String signup(User user) {
//         if (userRepository.findByUsername(user.getUsername()) == null) {
//             userRepository.save(user);
//             return "Signup successful";
//         }
//         return "Username already exists";
//     }



// }
