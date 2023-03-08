package com.system.agb.SystemAnimalCoupB.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="solicitudes")
public class Solicitud implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_solicitud")
    private Integer id_solicitud;

    @Column(name="fecha")
    private LocalDateTime fecha;

    @Column(name="tipopago")
    private String tipopago;

    @Column(name="comentario")
    private String comentario;

    @Column(name="estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name="id_persona",referencedColumnName ="id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name="id_animal",referencedColumnName ="id_animal")
    private Animal animal;
}
