package com.rollerspeed.rollerspeed.models;

import jakarta.persistence.*;

@Entity
@Table(name = "instructores") 
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instructor")
    private Long idInstructor;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    private String especialidad;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private User user;

    // --- Constructores, Getters y Setters ---

    public Long getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Long idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}