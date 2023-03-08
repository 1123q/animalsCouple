package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface    AnimalService extends  GenericService<Animal, Integer>{

    List<Animal> findByTipoId(Integer tipoId);

    List<Animal> findByRazaId(Integer id_raza);

    List<Animal> findByTipoIdAndNombreLike(Integer tipoId, String nombreraza);

    List<Animal> findAllAnimalsIdAndNombreLike(String genericName);

    List<Animal> findAllAnimals();
    List<Animal> findByUser (Integer id_persona);

    List<Animal> findByTipoIdAndGeneroLike(Integer tipoId, String genero);

    //Implementaicon de los filtros
    List<Animal> findAnimalesByRazaAndTipo(Integer id_tipo, List<Integer> razas);

    //uttimo filtro para el genero de ainimal
    List<Animal> findByRazaIdAndGenero(Integer id_raza, String gender);

    public Integer updateAnimalNumSoliAndDisponibilidadById(Integer id_animal, Integer num_soli, Boolean disponibilidad);

    //Metodo para la busqueda por letra.
    List<Animal> findByEstadoAndRaAnimal_RazaAnimal_NombretipoStartingWithIgnoreCase(String letra);

    //Para traer toda la lista de los animales perteneciente al id de persona.
    public List<Animal> findAnimalByPersona(Persona persona);

    //Para actualizar el estado del animal
    public Integer updateAnimalEstadoById(@Param("id_animal") Integer id_animal);
}
