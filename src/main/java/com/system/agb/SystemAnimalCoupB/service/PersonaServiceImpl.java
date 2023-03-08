package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Integer> implements PersonaService{

        @Autowired
        private PersonaRepository personaRepository;

        @Override
        public CrudRepository<Persona, Integer> getDao() {
            return personaRepository;
        }

        //Implementacion de los metodos para verificar a la persona
        @Override
        public Persona findByCedula(String cedula) {
                return personaRepository.findByCedula(cedula);
        }

        @Override
        public Boolean existsByCorreo(String correo) {
                if(personaRepository.existsByCorreo(correo)){
                        return true;
                }
                return false;
        }

        @Override
        public Integer updatePicture(String foto, int id_persona) {
                return personaRepository.updateFoto(foto, id_persona);
        }

        @Override
        public List<Persona> findByCorreoNotificaAndTipoAnimal(Integer id_tipo) {
                return personaRepository.findByCorreoNotificaAndTipoAnimal(id_tipo);
        }
}
