package com.rollerspeed.rollerspeed.services;

import com.rollerspeed.rollerspeed.models.User;
import com.rollerspeed.rollerspeed.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user) {
        // Cifra la contraseña antes de guardarla
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Actualiza este método para que use el cifrado
    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
