package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.RazaAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RazaAnimalRepository extends JpaRepository<RazaAnimal, Integer> {
    @Query("SELECT r FROM RazaAnimal r INNER JOIN r.RazaAnimal ta WHERE ta.id_tipoanimal = :id_tipoanimal")
    List<RazaAnimal> findByTipoAnimalFi(@Param("id_tipoanimal") Integer id_tipoanimal);

    @Query("SELECT r FROM RazaAnimal r WHERE r.id_razaanimal IN (:razasId)")
    List<RazaAnimal> findAllById_razaanimal(@Param("razasId") List<Integer> razasId);


    //List<RazaAnimal> findAllById_razaanimal()


}
