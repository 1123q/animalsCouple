package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.Chat;
import com.system.agb.SystemAnimalCoupB.model.Mensaje;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.service.ChatService;
import com.system.agb.SystemAnimalCoupB.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private PersonaService personaService;

    @GetMapping("/chat/finduser/exist/{idPersona1}/{idPersona2}")
    public ResponseEntity<?> getChatByPersonasInSection(@PathVariable("idPersona1") Integer idPersona1, @PathVariable("idPersona2") Integer idPersona2) {
        Persona persona1 = personaService.findById(idPersona1);
        Persona persona2 = personaService.findById(idPersona2);
        Chat chat = chatService.getChatByPersonas(persona1, persona2);
        if (chat != null) {
            return ResponseEntity.ok(chat);
        } else {
            return ResponseEntity.ok(null);
            //return ResponseEntity.notFound().build();
        }
    }

    //Segunda opcion para la busqueda de un chat existente que geenera un inconveniente.
    @GetMapping("/chat/find/{idPersonaEmisor}/{idPersonaReceptor}")
    public List<Chat> TgetChatByPersonasInSectionOP(@PathVariable("idPersonaEmisor") Integer idPersonaEmisor, @PathVariable("idPersonaReceptor") Integer idPersonaReceptor) {
        Persona personaEmisor = personaService.findById(idPersonaEmisor);
        Persona personaReceptor = personaService.findById(idPersonaReceptor);
        List<Chat> chat = chatService.getChatsByPersonas(personaEmisor, personaReceptor);
        if(chat == null){
            return chatService.findChatsByPersonasReceptor(personaEmisor, personaReceptor);
           // return chatService.getChatsByPersonas(personaEmisor, personaReceptor);
        }
        return chat;

    }
    //Fin del metodo para verificar
    @GetMapping("/chat/{idPersonaEmisor}/{idPersonaReceptor}")
    public List<Chat> getChatsByPersonas(@PathVariable("idPersonaEmisor") Integer idPersonaEmisor, @PathVariable("idPersonaReceptor") Integer idPersonaReceptor) {
        Persona personaEmisor = personaService.findById(idPersonaEmisor);
        Persona personaReceptor = personaService.findById(idPersonaReceptor);
        return chatService.getChatsByPersonas(personaEmisor, personaReceptor);
    }

    @GetMapping("/chat/{id_persona}/chats")
    public List<Chat> getChatsByPersona(@PathVariable("id_persona") Integer id_persona) {
        Persona persona = personaService.findById(id_persona);
        return chatService.findChatsByPersona(persona);
    }

    @PostMapping("/chat/{idPersonaEmisor}/{idPersonaReceptor}")
    public Chat createChat(@PathVariable("idPersonaEmisor") Integer idPersonaEmisor, @PathVariable("idPersonaReceptor") Integer idPersonaReceptor) {
        Persona personaEmisor = personaService.findById(idPersonaEmisor);
        Persona personaReceptor = personaService.findById(idPersonaReceptor);
        return chatService.createChat(personaEmisor, personaReceptor);
    }

    @GetMapping("/chat/{idChat}/mensajes")
    public List<Mensaje> getMensajesByChat(@PathVariable("idChat") Integer idChat) {
        Chat chat = chatService.getChatById(idChat);
        List<Mensaje> mensajes = chatService.getMensajesByChat(chat);
        //return chatService.getMensajesByChat(chat);
        return mensajes;
    }

    @PostMapping("/chat/{idChat}/mensajes")
    public Mensaje createMensaje(@PathVariable("idChat") Integer idChat, @RequestBody Mensaje mensaje) {
        Chat chat = chatService.getChatById(idChat);
        return chatService.createMensaje(chat, mensaje.getTexto());
    }

    //Para guardar la infirmacio mediante el id de usuario y la fecha correspodniene
    @PostMapping("/chat/{idChat}/mensajes/{id_persona}")
    public Mensaje createMensajeIsPresent(@PathVariable("idChat") Integer idChat, @PathVariable("id_persona") Integer id_persona, @RequestBody Mensaje mensaje) {
        Chat chat = chatService.getChatById(idChat);
        Persona persona = personaService.findById(id_persona);
        return chatService.createMensajeIdentify(chat, persona, mensaje.getTexto());
    }

    @GetMapping("/chat/findBychat/{idChat}")
    public Chat getByChat(@PathVariable("idChat") Integer idChat) {

        return chatService.findById_Chat(idChat);

    }

    @GetMapping("/chat/findChat/count/{id_persona}")
    public Integer getCountChatByIdPersona(@PathVariable("id_persona") Integer id_persona){
            return chatService.findChatByCountNumChatPersona(id_persona);
    }

}
