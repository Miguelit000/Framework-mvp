package com.rollerspeed.rollerspeed.repositories;

import com.rollerspeed.rollerspeed.models.ClaseEstudiante;
import com.rollerspeed.rollerspeed.models.ClaseEstudianteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaseEstudianteRepository extends JpaRepository<ClaseEstudiante, ClaseEstudianteId> {
    List<ClaseEstudiante> findByClaseIdClase(Long idClase);
    
}
