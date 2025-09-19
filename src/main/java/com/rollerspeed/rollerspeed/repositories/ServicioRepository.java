package com.rollerspeed.rollerspeed.repositories;

import com.rollerspeed.rollerspeed.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    /**
     * Busca todos los servicios que están marcados como activos.
     * Es útil para mostrar solo los servicios disponibles al público.
     * @return Una lista de servicios activos.
     */
    List<Servicio> findByActivoTrue();

}
