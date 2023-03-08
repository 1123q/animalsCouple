package com.system.agb.SystemAnimalCoupB.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="solicitudesctivacion")
public class SolicitudActivacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_solicitud_activacion")
    private Integer id_solicitud_activacion;

    @Column(name="estado")
    private Boolean estado;

    @Column(name="fecha")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name="id_cuenta",referencedColumnName ="id_cuenta")
    private Cuenta cuenta;
}
