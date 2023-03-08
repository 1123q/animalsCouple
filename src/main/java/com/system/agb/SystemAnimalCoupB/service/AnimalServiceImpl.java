package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AnimalServiceImpl extends GenericServiceImpl<Animal, Integer> implements AnimalService{

        @Autowired
        private AnimalRepository animalRepository;

        @Override
        public CrudRepository<Animal, Integer> getDao() {
            return animalRepository;
        }

        @Override
        public List<Animal> findByTipoId(Integer tipoId) {
                return animalRepository.findByTipoId(tipoId);
        }

        @Override
        public List<Animal> findByRazaId(Integer id_raza) {
                return animalRepository.findByRazaId(id_raza);
        }

        @Override
        public List<Animal> findByTipoIdAndNombreLike(Integer tipoId, String nombreraza) {
                return animalRepository.findByTipoIdAndNombreLike(tipoId, nombreraza);
        }

        @Override
        public List<Animal> findAllAnimalsIdAndNombreLike(String genericName) {
                return animalRepository.findsLike(genericName, genericName, genericName);
        }

        @Override
        public List<Animal> findAllAnimals() {
                return animalRepository.findAllAnimals();
        }

        @Override
        public List<Animal> findByUser(Integer id_persona) {
                return animalRepository.findByUser(id_persona);
        }

        @Override
        public List<Animal> findByTipoIdAndGeneroLike(Integer tipoId, String genero) {
                return animalRepository.findByRaAnimalIdAndGeneroLike(tipoId, genero);
        }

        @Override
        public List<Animal> findAnimalesByRazaAndTipo(Integer id_tipo, List<Integer> razas) {
                return animalRepository.findAnimalesByRazaAndTipo(id_tipo, razas);
        }

        @Override
        public List<Animal> findByRazaIdAndGenero(Integer id_raza, String gender) {
                return animalRepository.findByRazaIdAndGenero(id_raza, gender);
        }

        @Override
        @Transactional
        public Integer updateAnimalNumSoliAndDisponibilidadById(Integer id_animal, Integer num_soli, Boolean disponibilidad) {
                return animalRepository.updateAnimalNumSoliAndDisponibilidadById(id_animal, num_soli, disponibilidad);
        }

        //En esta parte estamos haciendo el uso del transaction para el permiso de update por que no se abrio ningunp puerto para la actualizacion.

        @Override
        public List<Animal> findByEstadoAndRaAnimal_RazaAnimal_NombretipoStartingWithIgnoreCase(String letra) {
                return animalRepository.findByEstadoAndRaAnimal_RazaAnimal_NombretipoStartingWithIgnoreCase(letra);
        }

        @Override
        public List<Animal> findAnimalByPersona(Persona persona) {
                return animalRepository.findAnimalByPersona(persona);
        }

        @Override
        @Transactional
        public Integer updateAnimalEstadoById(Integer id_animal) {
                return animalRepository.updateAnimalEstadoById(id_animal, false);
        }
}
