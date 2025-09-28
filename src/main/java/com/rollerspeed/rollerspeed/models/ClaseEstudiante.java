package com.rollerspeed.rollerspeed.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clase_estudiante")
public class ClaseEstudiante {

    @EmbeddedId
    private ClaseEstudianteId id;

    @ManyToOne
    @MapsId("claseId")
    @JoinColumn(name = "id_clase")
    private Clase clase;

    @ManyToOne
    @MapsId("alumnoId")
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    public ClaseEstudianteId getId() {
        return id;
    }

    public void setId(ClaseEstudianteId id) {
        this.id = id;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    
}
