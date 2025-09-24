package com.rollerspeed.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }

    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // A. Rutas públicas: cualquiera puede acceder a ellas
                .requestMatchers("/", "/login", "/registro", "/css/**", "/js/**", "/images/**", "/servicios", "/mision", "/vision", "/valores", "/eventos", "/conocenos").permitAll()
                
                // B. Rutas de administrador: solo usuarios con el rol 'ADMIN' pueden acceder
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // C. Cualquier otra ruta: el usuario debe estar autenticado (haber iniciado sesión)
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // D. Le decimos a Spring dónde está nuestra página de login
                .loginPage("/login")
                .usernameParameter("email")
                // A dónde ir si el login es exitoso
                .defaultSuccessUrl("/bienvenida", true)
                // Permite a todos ver la página de login
                .permitAll()
            )
            .logout(logout -> logout
                // Permite a todos usar la función de logout
                .permitAll()
            );

        return http.build();
    }
}

