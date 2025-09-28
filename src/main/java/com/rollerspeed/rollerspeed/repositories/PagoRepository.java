package com.rollerspeed.rollerspeed.repositories;

import com.rollerspeed.rollerspeed.models.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    
}
