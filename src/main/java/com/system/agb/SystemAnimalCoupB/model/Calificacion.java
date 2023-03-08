package com.system.agb.SystemAnimalCoupB.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="calificaciones")
public class Calificacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_calificacion")
    private Integer id_calificacion;

    @Column(name="numsolicitudes")
    private Integer numsolicitudes;

    @ManyToOne
    @JoinColumn(name="id_animal",referencedColumnName ="id_animal")
    private Animal animal;

}
