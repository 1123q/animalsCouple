package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Persona;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaService extends  GenericService<Persona, Integer>{

    public Persona findByCedula(String cedula);

    public Boolean existsByCorreo(String correo);

    public Integer updatePicture(String foto, int id_persona);

    //Por el momento solo va ser una consulta solo de prueba..
    List<Persona> findByCorreoNotificaAndTipoAnimal(Integer id_tipo);

}
