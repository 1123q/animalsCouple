package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.Chat;
import com.system.agb.SystemAnimalCoupB.model.Cuenta;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    //Podemos identificar si el usuario existe o no en el sistema..
    public Boolean existsByUsuario(String user);

    //No permite hacer una busqueda mediante JPA por user and password..
    public Cuenta findByUsuarioAndContrasenia(String user, String password);

    public Cuenta findByUsuario(String user);

    public List<Cuenta> findByPersona(Persona persona);

    @Query("SELECT c FROM Cuenta c WHERE c.usuario <> 'ADMIN'")
    public List<Cuenta> findAccountsByUserDiferentAdmin();

    @Query("SELECT c FROM Cuenta c INNER JOIN c.persona p WHERE p.cedula = :cedula")
    public Cuenta findByPersonaAccount(@Param("cedula") String cedula);

    @Query("SELECT c FROM Cuenta c INNER JOIN c.persona p WHERE p.id_persona = :id_persona")
    public Cuenta findByUserIdAccountChat(@Param("id_persona") Integer id_persona);


}
