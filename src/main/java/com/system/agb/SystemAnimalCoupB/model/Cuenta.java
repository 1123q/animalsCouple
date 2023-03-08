package com.system.agb.SystemAnimalCoupB.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="cuentas")
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cuenta")
    private Integer id_cuenta;

    @Column(name="usuario")
    private String usuario;

    @Column(name="contrasenia")
    private String contrasenia;

    @Column(name="estado")
    private boolean estado;

    @Column(name="rol")
    private String rol;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_persona", referencedColumnName ="id_persona")
    private Persona persona;

    @JsonIgnore
    @OneToMany(mappedBy="cuenta")
    private List<SolicitudActivacion> solicitudActivacions;


}
