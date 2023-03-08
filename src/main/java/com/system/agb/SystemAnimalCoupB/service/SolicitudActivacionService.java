package com.system.agb.SystemAnimalCoupB.service;

import com.system.agb.SystemAnimalCoupB.model.SolicitudActivacion;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudActivacionService extends  GenericService<SolicitudActivacion, Integer>{
    public SolicitudActivacion findBySolicitudActivacion(Integer id_cuenta);

    public List<SolicitudActivacion> findBySolicitudActivacionTrue();

    SolicitudActivacion findBySolicitudActivacionTrueDate(Integer id_cuenta);
}
