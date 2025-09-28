package com.rollerspeed.rollerspeed.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alumnos") // Tabla alumnos
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno") // El nombre en la BD
    private Long idAlumno;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento; // LocalDate para fechas

    @Column(nullable = false, length = 20)
    private String genero;

    @Column(length = 20)
    private String telefono;
    
    // Relación con Usuario 
    @OneToOne(cascade = CascadeType.ALL) // Si borramos un alumno, también se borra su usuario.
    @JoinColumn(name = "id_usuario", referencedColumnName = "id") // conecxion tablas
    private User user;


    // Constructores, Getters y Setters

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}