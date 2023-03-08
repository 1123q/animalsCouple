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
@Table(name="razasanimales")
public class RazaAnimal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_razaanimal")
    private Integer id_razaanimal;

    @Column(name="nombreraza")
    private String nombreraza;

    @Column(name="descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="id_tipoanimal",referencedColumnName ="id_tipoanimal")
    private TipoAnimal RazaAnimal;

    @JsonIgnore
    @OneToMany (mappedBy = "raAnimal")
    private List<Animal> animales;

    //Implemetacion de la relacion con la preferencia..
    @JsonIgnore
    @OneToMany(mappedBy = "razaAnimal")
    private List<Preferencia> preferencia;
}
