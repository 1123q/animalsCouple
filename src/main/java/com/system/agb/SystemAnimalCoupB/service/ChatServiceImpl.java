package com.system.agb.SystemAnimalCoupB.service;

import com.system.agb.SystemAnimalCoupB.model.Chat;
import com.system.agb.SystemAnimalCoupB.model.Mensaje;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.repository.ChatRepository;
import com.system.agb.SystemAnimalCoupB.repository.MensajeRepository;
import com.system.agb.SystemAnimalCoupB.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Chat> findChatsByPersonasReceptor(Persona personaReceptor, Persona personaEmisor) {
        return chatRepository.findChatsByPersonasReceptor(personaReceptor, personaEmisor);
    }

    @Override
    public List<Chat> getChatsByPersonas(Persona persona_emisor, Persona persona_receptor) {
        return chatRepository.findChatsByPersonas(persona_emisor, persona_receptor);
    }
    @Override
    public Chat getChatById(Integer idChat) {
        return chatRepository.findById(idChat).orElse(null);
    }
    @Override
    public Chat createChat(Persona persona_emisor, Persona persona_receptor) {
        Chat chat = new Chat();
        chat.setPersona_emisor(persona_emisor);
        chat.setPersona_receptor(persona_receptor);
        return chatRepository.save(chat);
    }
    @Override
    public List<Mensaje> getMensajesByChat(Chat chat) {
        return mensajeRepository.findByChat(chat);
    }
    @Override
    public Mensaje createMensaje(Chat chat, String texto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chat);
        mensaje.setTexto(texto);
        return mensajeRepository.save(mensaje);
    }

    @Override
    public List<Chat> findChatsByPersona(Persona persona) {
        return chatRepository.findChatsByPersona(persona, persona);
    }

    @Override
    public Mensaje createMensajeIdentify(Chat chat, Persona persona, String texto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chat);
        mensaje.setPersona(persona);
        mensaje.setTexto(texto);
        mensaje.setFecha(LocalDateTime.now());
        return mensajeRepository.save(mensaje);
    }

    @Override
    public Chat findById_Chat(Integer id_chat) {
        return chatRepository.findById(id_chat).orElse(null);
    }

    @Override
    public Chat getChatByPersonas(Persona persona1, Persona persona2) {
        Optional<Chat> chat = chatRepository.findChatByPersonas(persona1, persona2);
        return chat.orElse(null);
    }

    @Override
    public Integer findChatByCountNumChatPersona(Integer id_persona) {
        return chatRepository.findChatByCountNumChatPersona(id_persona, id_persona);
    }
}


