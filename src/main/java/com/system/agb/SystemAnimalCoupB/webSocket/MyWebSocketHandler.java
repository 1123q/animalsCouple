package com.system.agb.SystemAnimalCoupB.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.agb.SystemAnimalCoupB.model.Chat;
import com.system.agb.SystemAnimalCoupB.model.Mensaje;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.service.ChatService;
import com.system.agb.SystemAnimalCoupB.service.PersonaService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyWebSocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final ChatService chatService;
    private final PersonaService personaService;

    public MyWebSocketHandler(ChatService chatService, PersonaService personaService) {
        this.chatService = chatService;
        this.personaService = personaService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Mensaje mensaje = mapper.readValue(message.getPayload(), Mensaje.class);

        Chat chat = chatService.getChatById(mensaje.getChat().getId_chat());
        Persona persona = personaService.findById(mensaje.getPersona().getId_persona());

        chatService.createMensajeIdentify(chat, persona, mensaje.getTexto());

        TextMessage response = new TextMessage(mapper.writeValueAsString(mensaje));
        for (WebSocketSession s : sessions) {
            s.sendMessage(response);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
