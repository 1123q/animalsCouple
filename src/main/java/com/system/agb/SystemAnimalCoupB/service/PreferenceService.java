package com.system.agb.SystemAnimalCoupB.service;

import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.model.Preferencia;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreferenceService extends GenericService<Preferencia, Integer>{
    List<Preferencia> findAllByPersona(Persona persona);

    //Implementacion de los metodos de lso filtros.
    List<Object[]> findDistinctAnimalTypesByUser(@Param("idPersona") Integer idPersona);

    List<Object[]> findDistinctAnimalTypesByUserAndTiporaza(Integer idPersona, Integer id_tipo);

    List<Animal> findmispreferencias(Integer idPersona);
}
