package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    public Persona findByCedula(String cedula);

    public  Boolean existsByCorreo(String correo);

    @Modifying
    @Transactional
    @Query("UPDATE Persona p SET p.foto = :foto WHERE p.id_persona = :id_persona")
    int updateFoto(@Param("foto") String foto, @Param("id_persona") Integer id_persona);


//Para la implementacion del chat

    @Query("SELECT DISTINCT p FROM Persona p INNER JOIN p.preferencia pr INNER JOIN pr.razaAnimal r INNER JOIN r.RazaAnimal t WHERE p.correo_notifica = true AND t.id_tipoanimal = :id_tipo")
    List<Persona> findByCorreoNotificaAndTipoAnimal(@Param("id_tipo") Integer id_tipo);



    //Este es el que me genera inconvenientes
   // public Persona findById_persona(Integer idPersona);
    List<Persona> findAll();

}