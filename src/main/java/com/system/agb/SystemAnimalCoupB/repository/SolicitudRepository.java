package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
//edi
@Query("SELECT s FROM Solicitud s JOIN s.animal a WHERE s.estado = :estado AND a.persona.id_persona = :id_persona")
List<Solicitud> findSolicitudesPendientesByPersona(@Param("estado") String estado, @Param("id_persona") Integer idPersona);

    @Query("SELECT s FROM Solicitud s  WHERE s.estado = :estado AND s.persona.id_persona = :id_persona")
    List<Solicitud> findSolicitudesByEsPersona(@Param("estado") String estado, @Param("id_persona") Integer idPersona);
}
