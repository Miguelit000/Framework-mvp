package com.rollerspeed.rollerspeed.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClaseEstudianteId implements Serializable {

    @Column(name = "id_clase")
    private Long claseId;

    @Column(name = "id_alumno")
    private Long alumnoId;

    public Long getClaseId() {
        return claseId;
    }

    public void setClaseId(Long claseId) {
        this.claseId = claseId;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaseEstudianteId that = (ClaseEstudianteId) o;
        return Objects.equals(claseId, that.claseId) && Objects.equals(alumnoId, that.alumnoId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(claseId, alumnoId);
    }
}
