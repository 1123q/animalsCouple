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
@Table(name="chats")
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_chat")
    private Integer id_chat;

    @ManyToOne
    @JoinColumn(name = "id_persona_emisor")
    private Persona persona_emisor;

    @ManyToOne
    @JoinColumn(name = "id_persona_receptor")
    private Persona persona_receptor;

    @JsonIgnore
    @OneToMany(mappedBy="chat", fetch=FetchType.LAZY)
    private List<Mensaje> mensajes;
}

