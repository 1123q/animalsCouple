package com.system.agb.SystemAnimalCoupB.service;

import com.system.agb.SystemAnimalCoupB.model.Chat;
import com.system.agb.SystemAnimalCoupB.model.Mensaje;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatService {

    public List<Chat> findChatsByPersonasReceptor(Persona personaReceptor, Persona personaEmisor);
    public List<Chat> getChatsByPersonas(Persona persona_emisor, Persona persona_receptor);

    public Chat getChatById(Integer idChat);

    public Chat createChat(Persona persona_emisor, Persona persona_receptor);

    public List<Mensaje> getMensajesByChat(Chat chat);

    public Mensaje createMensaje(Chat chat, String texto);

    //Implementaion de los demas metodos para el chat
    List<Chat> findChatsByPersona( Persona persona);

    public Mensaje createMensajeIdentify(Chat chat, Persona persona, String texto);

    public  Chat findById_Chat(Integer id_chat);

    public Chat getChatByPersonas(Persona persona1, Persona persona2);

    public Integer findChatByCountNumChatPersona(Integer id_persona);
}
