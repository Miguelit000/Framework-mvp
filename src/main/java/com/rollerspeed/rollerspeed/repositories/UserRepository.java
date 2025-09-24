package com.rollerspeed.rollerspeed.repositories;

import com.rollerspeed.rollerspeed.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Spring Security usa este para encontrar al usuario
    User findByEmail(String email);

    // El método findByEmailAndPassword ya no es necesario aquí.
}