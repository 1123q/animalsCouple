package com.system.agb.SystemAnimalCoupB.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="animales")
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_animal")
    private Integer id_animal;

    @Column(name="nombre")
    private String nombre;

    @Column(name = "foto", columnDefinition = "LONGBLOB")
    private String foto;

    @Column(name="genero")
    private String genero;


    @Column(name="color")
    private String color;

    @Column(name="edad")
    private Integer edad;

    @Column(name="preciosolicitud")
    private String preciosolicitud;

    @Column(name="peso")
    private Double peso;

    @Column(name="disponibilidad")
    private Boolean disponibilidad;

    @Column(name="estado")
    private Boolean estado;

    @Column(name="num_soli")
    private Integer num_soli;

    @ManyToOne
    @JoinColumn(name="id_razaanimal",referencedColumnName ="id_razaanimal")
    private RazaAnimal raAnimal;

    @ManyToOne
    @JoinColumn(name="id_persona",referencedColumnName ="id_persona")
    private Persona persona;
    //one to one
    @ManyToOne
    @JoinColumn(name="id_fichamedica",referencedColumnName ="id_fichamedica")
    private FichaMedica fichaMedica;

    @JsonIgnore
    @OneToMany (mappedBy = "animal")
    private List<Solicitud> solicituds;

    @JsonIgnore
    @OneToMany (mappedBy = "animal")
    private List<Calificacion> calificaciones;



}
