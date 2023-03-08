package com.system.agb.SystemAnimalCoupB.repository;

import com.system.agb.SystemAnimalCoupB.model.RazaAnimal;
import com.system.agb.SystemAnimalCoupB.model.Solicitud;
import com.system.agb.SystemAnimalCoupB.model.SolicitudActivacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudActivacionRepository extends JpaRepository<SolicitudActivacion, Integer> {

    @Query("SELECT s FROM SolicitudActivacion s WHERE s.cuenta.id_cuenta = (:id_cuenta)")
    SolicitudActivacion findBySolicitudActivacion(@Param("id_cuenta") Integer id_cuenta);

    @Query("SELECT s FROM SolicitudActivacion s WHERE s.estado = false ORDER BY s.fecha")
    List<SolicitudActivacion> findBySolicitudActivacionTrue();


    @Query("SELECT s FROM SolicitudActivacion s WHERE s.cuenta.id_cuenta = (:id_cuenta) and s.estado= false")
    SolicitudActivacion findBySolicitudActivacionTrueDate(@Param("id_cuenta") Integer id_cuenta);
}
