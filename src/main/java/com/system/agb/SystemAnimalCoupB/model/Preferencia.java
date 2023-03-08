package com.system.agb.SystemAnimalCoupB.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "preferencias")
public class Preferencia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_preferencia")
    private Integer id_preferencia;

    @Column(name = "estado")
    private  Boolean estado;

    //Implementacion de la relacion con persona

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Persona persona;

    //Implementación de la realción con la raza.

    @ManyToOne
    @JoinColumn(name = "id_razaanimal", referencedColumnName = "id_razaanimal")
    private RazaAnimal razaAnimal;
}
