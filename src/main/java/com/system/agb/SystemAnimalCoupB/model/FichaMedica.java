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
@Table(name="fichasmedicas")
public class FichaMedica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_fichamedica")
    private Integer id_fichamedica;

   // @Lob
    @Column(name = "documento", columnDefinition = "LONGBLOB")
    private byte[] documento;

    @JsonIgnore
    @OneToMany (mappedBy = "fichaMedica")
    private List<Animal> animales;



}
