package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.Chat;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    //List<Chat> findByPersona_emisorAndPersona_receptor(Persona persona_emisor, Persona persona_receptor);
   // List<Chat> findByPersonaEmisorAndPersonaReceptor(Persona personaEmisor, Persona personaReceptor);

    @Query("SELECT c FROM Chat c WHERE c.persona_emisor = :personaEmisor OR c.persona_receptor = :personaReceptor")
    List<Chat> findChatsByPersona(@Param("personaEmisor") Persona personaEmisor, @Param("personaReceptor") Persona personaReceptor);
    @Query("SELECT c FROM Chat c WHERE c.persona_emisor = :personaEmisor AND c.persona_receptor = :personaReceptor")
    List<Chat> findChatsByPersonas(@Param("personaEmisor") Persona personaEmisor, @Param("personaReceptor") Persona personaReceptor);

    @Query("SELECT c FROM Chat c WHERE c.persona_receptor = :personaReceptor AND c.persona_emisor = :personaEmisor")
    List<Chat> findChatsByPersonasReceptor(@Param("personaReceptor") Persona personaReceptor, @Param("personaEmisor") Persona personaEmisor);


    //Implementacion para validar los chats en doble

    @Query("SELECT c FROM Chat c WHERE (c.persona_emisor = :persona1 AND c.persona_receptor = :persona2) OR (c.persona_emisor = :persona2 AND c.persona_receptor = :persona1)")
    Optional<Chat> findChatByPersonas(@Param("persona1") Persona persona1, @Param("persona2") Persona persona2);


    //Trar el loc chats
    @Query("SELECT count(c) FROM Chat c WHERE c.persona_emisor.id_persona in :id_persona_emisor OR c.persona_receptor.id_persona in :id_persona_receptor")
    Integer findChatByCountNumChatPersona(@Param("id_persona_emisor") Integer id_persona_emisor, @Param("id_persona_receptor") Integer id_persona_receptor);


}
