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
@Table(name="tiposAnimales")
public class TipoAnimal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipoanimal")
    private Integer id_tipoanimal;

    @Column(name="nombretipo")
    private String nombretipo;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name = "foto", columnDefinition = "LONGBLOB")
    private String foto;


    @JsonIgnore
    @OneToMany (mappedBy = "RazaAnimal")
    private List<RazaAnimal> razaAnimals;
}
