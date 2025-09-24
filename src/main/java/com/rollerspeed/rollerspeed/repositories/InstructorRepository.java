package com.rollerspeed.rollerspeed.repositories;

import com.rollerspeed.rollerspeed.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
}
