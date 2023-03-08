package com.system.agb.SystemAnimalCoupB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="mensajes")
public class Mensaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_mensaje")
    private Integer id_mensaje;

    @Column(name="texto")
    private String texto;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_chat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    //@JsonIgnore
    private Persona persona;
}
