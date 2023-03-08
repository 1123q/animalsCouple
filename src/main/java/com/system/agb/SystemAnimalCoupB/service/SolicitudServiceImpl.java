package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Solicitud;
import com.system.agb.SystemAnimalCoupB.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SolicitudServiceImpl extends GenericServiceImpl<Solicitud, Integer> implements SolicitudService{
        @Autowired
        private SolicitudRepository solicitudRepository;

        @Override
        public CrudRepository<Solicitud, Integer> getDao() {
                return solicitudRepository;
        }

        @Override
        public List<Solicitud> findSolicitudesPendientesByPersona(String estado, Integer id_persona) {
                return solicitudRepository.findSolicitudesPendientesByPersona(estado, id_persona);
        }

        @Override
        public List<Solicitud> findSolicitudeByEsPersona(String estado, Integer id_persona) {
                return solicitudRepository.findSolicitudesByEsPersona(estado, id_persona);
        }
}
