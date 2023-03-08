package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.TipoAnimal;
import com.system.agb.SystemAnimalCoupB.repository.TipoAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class TipoAnimalServiceImpl extends GenericServiceImpl<TipoAnimal, Integer> implements TipoAnimalService{

        @Autowired
        private TipoAnimalRepository tipoAnimalRepository;

        @Override
        public CrudRepository<TipoAnimal, Integer> getDao() {
            return tipoAnimalRepository;
        }
}
