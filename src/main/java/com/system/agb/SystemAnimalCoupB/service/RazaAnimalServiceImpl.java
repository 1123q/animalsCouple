package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.RazaAnimal;
import com.system.agb.SystemAnimalCoupB.repository.RazaAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RazaAnimalServiceImpl extends GenericServiceImpl<RazaAnimal, Integer> implements RazaAnimalService{

        @Autowired
        private RazaAnimalRepository razaAnimalRepository;

        @Override
        public CrudRepository<RazaAnimal, Integer> getDao() {
            return razaAnimalRepository;
        }

        @Override
        public List<RazaAnimal> getAllRazaOfAnimal(Integer id) {
                return razaAnimalRepository.findByTipoAnimalFi(id);
        }

        @Override
        public List<RazaAnimal> findAllById(List<Integer> razasId) {
                return razaAnimalRepository.findAllById_razaanimal(razasId);
        }
}
