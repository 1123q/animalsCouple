package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.model.RazaAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    @Query("SELECT a FROM Animal a INNER JOIN a.raAnimal r INNER JOIN r.RazaAnimal t WHERE t.id_tipoanimal = :tipoId AND a.estado = true")
    List<Animal> findByTipoId(@Param("tipoId") Integer tipoId);

    @Query("SELECT a FROM Animal a INNER JOIN a.raAnimal r WHERE r.id_razaanimal = :id_raza AND a.estado = true")
    List<Animal> findByRazaId(@Param("id_raza") Integer id_raza);

    //Por el momento este metodo no se va poner en uso pero queda vijente para otro uso..
    @Query("SELECT a FROM Animal a INNER JOIN a.raAnimal r INNER JOIN r.RazaAnimal t WHERE t.id_tipoanimal = :tipoId AND a.estado = true AND r.nombreraza like %:nombreraza%")
    List<Animal> findByTipoIdAndNombreLike(@Param("tipoId") Integer tipoId, @Param("nombreraza") String nombreraza);

  //  @Query("SELECT a FROM Animal a INNER JOIN a.raAnimal r INNER JOIN r.RazaAnimal t WHERE a.estado = true AND r.nombreraza like %:nombreraza% OR t.nombretipo like %:nombretipo% OR a.nombre like %:nombreani%")
    //List<Animal> findsLike(@Param("nombreraza") String nombreraza, @Param("nombretipo") String nombretipo, @Param("nombreani") String nombreani);

    @Query("SELECT a FROM Animal a INNER JOIN a.raAnimal r INNER JOIN r.RazaAnimal t WHERE (r.nombreraza like %:nombreraza% OR t.nombretipo like %:nombretipo% OR a.nombre like %:nombreani%) AND a.estado = true")
    List<Animal> findsLike(@Param("nombreraza") String nombreraza, @Param("nombretipo") String nombretipo, @Param("nombreani") String nombreani);

    @Query("SELECT a FROM Animal a WHERE a.estado = true ")
    List<Animal> findAllAnimals();

    @Query("SELECT a FROM Animal a JOIN a.persona p WHERE p.id_persona = :id_persona")
    List<Animal> findByUser(@Param("id_persona") Integer id_persona);

    //Hacer el filtro por el genero del animal..
    @Query("SELECT a FROM Animal a INNER JOIN a.raAnimal r INNER JOIN r.RazaAnimal t WHERE t.id_tipoanimal = :tipoId AND a.estado = true AND a.genero = :genero")
    List<Animal> findByRaAnimalIdAndGeneroLike(@Param("tipoId") Integer tipoId, @Param("genero") String genero);



    //Todos los filtros para las preferencias de los usuario con los animales..--------------------------------------------------------------------------
    @Query("SELECT a FROM Animal a JOIN a.raAnimal r JOIN r.RazaAnimal t WHERE t.id_tipoanimal = :id_tipo AND r.id_razaanimal IN :razas AND a.estado = true")
    List<Animal> findAnimalesByRazaAndTipo(@Param("id_tipo") Integer id_tipo, @Param("razas") List<Integer> razas);

    //Implementación para que me traiga por el genero de animal..
    @Query("SELECT a FROM Animal a INNER JOIN a.raAnimal r WHERE r.id_razaanimal = :id_raza AND a.genero = :gender AND a.estado = true")
    List<Animal> findByRazaIdAndGenero(@Param("id_raza") Integer id_raza, @Param("gender") String gender);

    //Update

    @Modifying
    @Query("UPDATE Animal a SET a.num_soli = :num_soli, a.disponibilidad = :disponibilidad WHERE a.id_animal = :id_animal")
    int updateAnimalNumSoliAndDisponibilidadById(@Param("id_animal") Integer id_animal, @Param("num_soli") Integer num_soli, @Param("disponibilidad") Boolean disponibilidad);


    @Query("SELECT a FROM Animal a INNER JOIN a.raAnimal r INNER JOIN r.RazaAnimal t WHERE (t.nombretipo LIKE :letra%) AND a.estado = true")
    List<Animal> findByEstadoAndRaAnimal_RazaAnimal_NombretipoStartingWithIgnoreCase(@Param("letra") String letra);

    //Para traer tola la lista de los animales
    public List<Animal> findAnimalByPersona(Persona persona);

    @Modifying
    @Query("UPDATE Animal a SET a.estado = :estado WHERE a.id_animal = :id_animal")
    int updateAnimalEstadoById(@Param("id_animal") Integer id_animal, @Param("estado") Boolean estado);
}
