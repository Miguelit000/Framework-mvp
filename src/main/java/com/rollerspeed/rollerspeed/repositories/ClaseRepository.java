package com.rollerspeed.rollerspeed.repositories;

import com.rollerspeed.rollerspeed.models.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {
    
}
