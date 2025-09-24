package com.rollerspeed.rollerspeed.services;

import com.rollerspeed.rollerspeed.models.User;
import com.rollerspeed.rollerspeed.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 1. Spring Security llamará a este método cuando alguien intente iniciar sesión.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 2. Buscamos al usuario en nuestra base de datos por su email.
        User user = userRepository.findByEmail(email); // Necesitaremos crear este método en el repositorio.
        
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }

        // 3. Creamos una lista de "autoridades" o roles.
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (user.getRol() != null) {
            authorities.add(new SimpleGrantedAuthority(user.getRol().getNombre()));
        }

        // 4. Devolvemos un objeto UserDetails que Spring Security entiende.
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            authorities
        );
    }
}

