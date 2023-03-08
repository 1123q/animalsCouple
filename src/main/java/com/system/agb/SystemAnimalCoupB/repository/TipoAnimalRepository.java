package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoAnimalRepository extends JpaRepository<TipoAnimal, Integer> {
    @Query("SELECT t FROM TipoAnimal t LEFT JOIN FETCH t.razaAnimals")
    List<TipoAnimal> findAllWithRazas();
}
