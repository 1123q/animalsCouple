package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.model.Preferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PreferenceRepository extends JpaRepository<Preferencia, Integer> {

    List<Preferencia> findAllByPersona(Persona persona);

    @Transactional
    @Modifying
    @Query("DELETE FROM Preferencia p WHERE p = :preferencia")
    void deletePreferencia(@Param("preferencia") Preferencia preferencia);

    //----------------------------------------IMPLEMENTACIÓN PARA LOS FILTROS------------------------------------------------

    //Implementacion para que me traiga el id del tipo como el nombre del tipo.. en una colección
    @Query("SELECT DISTINCT t.id_tipoanimal, t.nombretipo FROM Animal a JOIN a.persona p JOIN p.preferencia pr JOIN pr.razaAnimal r JOIN r.RazaAnimal t WHERE p.id_persona = :idPersona")
    List<Object[]> findDistinctAnimalTypesByUser(@Param("idPersona") Integer idPersona);

    //Implementación para que me traiga las razas referentes a los tipos de animales que el user selected

    @Query("SELECT DISTINCT r.id_razaanimal, r.nombreraza FROM Animal a JOIN a.persona p JOIN p.preferencia pr JOIN pr.razaAnimal r JOIN r.RazaAnimal t WHERE p.id_persona = :idPersona AND t.id_tipoanimal = :id_tipo")
    List<Object[]> findDistinctAnimalTypesByUserAndTiporaza(@Param("idPersona") Integer idPersona, @Param("id_tipo") Integer id_tipo);

    @Query("SELECT a FROM Animal a, Preferencia p WHERE a.raAnimal.id_razaanimal = p.razaAnimal.id_razaanimal AND p.persona.id_persona = :id_persona")
    List<Animal> findmispreferencias(@Param("id_persona") Integer id_persona);


}
