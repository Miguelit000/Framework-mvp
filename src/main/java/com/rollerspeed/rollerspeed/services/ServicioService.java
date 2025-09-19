package com.rollerspeed.rollerspeed.services;

import com.rollerspeed.rollerspeed.models.Servicio;
import com.rollerspeed.rollerspeed.repositories.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    /**
     * Obtiene todos los servicios existentes, tanto activos como inactivos.
     * Ideal para un panel de administrador.
     * @return Lista de todos los servicios.
     */
    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    /**
     * Obtiene solo los servicios que están activos.
     * Ideal para mostrar al público general.
     * @return Lista de servicios activos.
     */
    public List<Servicio> findActiveServices() {
        return servicioRepository.findByActivoTrue();
    }
    
    /**
     * Busca un servicio por su ID.
     * @param id El ID del servicio a buscar.
     * @return Un Optional que contiene el servicio si se encuentra.
     */
    public Optional<Servicio> findById(Long id) {
        return servicioRepository.findById(id);
    }

    /**
     * Guarda un nuevo servicio o actualiza uno existente.
     * @param servicio El objeto Servicio a guardar.
     * @return El servicio guardado (con su ID asignado si es nuevo).
     */
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }
    
    /**
     * Actualiza un servicio existente.
     * @param id El ID del servicio a actualizar.
     * @param servicioDetails Los nuevos datos para el servicio.
     * @return Un Optional con el servicio actualizado, o vacío si no se encontró.
     */
    public Optional<Servicio> update(Long id, Servicio servicioDetails) {
        return servicioRepository.findById(id)
                .map(servicioExistente -> {
                    servicioExistente.setNombre(servicioDetails.getNombre());
                    servicioExistente.setDescripcion(servicioDetails.getDescripcion());
                    servicioExistente.setPrecio(servicioDetails.getPrecio());
                    servicioExistente.setImageUrl(servicioDetails.getImageUrl());
                    servicioExistente.setActivo(servicioDetails.isActivo());
                    return servicioRepository.save(servicioExistente);
                });
    }

    /**
     * Elimina un servicio de la base de datos.
     * Nota: En aplicaciones reales, a menudo se prefiere desactivar ('soft delete').
     * @param id El ID del servicio a eliminar.
     */
    public void deleteById(Long id) {
        servicioRepository.deleteById(id);
    }
}