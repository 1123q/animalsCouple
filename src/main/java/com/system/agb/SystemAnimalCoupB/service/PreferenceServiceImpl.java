package com.system.agb.SystemAnimalCoupB.service;

import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.model.Preferencia;
import com.system.agb.SystemAnimalCoupB.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceServiceImpl extends GenericServiceImpl<Preferencia, Integer> implements PreferenceService {

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Override
    public CrudRepository<Preferencia, Integer> getDao() {
        return preferenceRepository;
    }

    @Override
    public List<Preferencia> findAllByPersona(Persona persona) {
        return preferenceRepository.findAllByPersona(persona);
    }

    //Implementacion para los filtros..
    @Override
    public List<Object[]> findDistinctAnimalTypesByUser(Integer idPersona) {
        return preferenceRepository.findDistinctAnimalTypesByUser(idPersona);
    }

    @Override
    public List<Object[]> findDistinctAnimalTypesByUserAndTiporaza(Integer idPersona, Integer id_tipo) {
        return preferenceRepository.findDistinctAnimalTypesByUserAndTiporaza(idPersona, id_tipo);
    }

    @Override
    public List<Animal> findmispreferencias(Integer idPersona) {
        return preferenceRepository.findmispreferencias(idPersona);
    }
}
