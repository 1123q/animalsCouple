package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Solicitud;

import com.system.agb.SystemAnimalCoupB.model.Solicitud;

import java.util.List;

public interface SolicitudService extends  GenericService<Solicitud, Integer>{

    List<Solicitud> findSolicitudesPendientesByPersona(String estado, Integer id_persona);

    List<Solicitud> findSolicitudeByEsPersona(String estado, Integer id_persona);
}