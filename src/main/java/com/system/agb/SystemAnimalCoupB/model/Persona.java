package com.system.agb.SystemAnimalCoupB.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="personas")
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona")
    private Integer id_persona;

    @Column(name="cedula")
    private String cedula;

    @Column(name="nombres")
    private String nombres;

    @Column(name="genero")
    private String genero;

    @Column(name="direccion")
    private String direccion;

    @Column(name="telefono")
    private String telefono;

    @Column(name="celular")
    private String celular;

    @Column(name="correo")
    private String correo;

    @Column(name="edad")
    private Date edad;

    @Column(name= "correo_notifica")
    private Boolean correo_notifica;

    @Column(name = "foto", columnDefinition = "LONGBLOB")
    private String foto;
    //private byte[] foto;

    @JsonIgnore
    @OneToMany (mappedBy = "persona")
    private List<Solicitud> solicituds;

    @JsonIgnore
    @OneToMany (mappedBy = "persona")
    private List<Animal> animals;

    @JsonIgnore
    @OneToMany (mappedBy = "persona")
    private List<Cuenta> cuentas;

    //Implementacion de la relacion con preferencia.
    @JsonIgnore
    @OneToMany(mappedBy = "persona")
    private List<Preferencia> preferencia;

    //implementación para el chat
    @JsonIgnore
  //  @OneToMany(mappedBy="persona_emisor", fetch=FetchType.LAZY)
    @OneToMany(mappedBy="persona_emisor")
    private List<Chat> chats_emisor;

    @JsonIgnore
   // @OneToMany(mappedBy="persona_receptor", fetch=FetchType.LAZY)
    @OneToMany(mappedBy="persona_receptor")
    private List<Chat> chats_receptor;

    @JsonIgnore
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;
}
