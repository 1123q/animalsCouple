package com.system.agb.SystemAnimalCoupB.service;

import com.system.agb.SystemAnimalCoupB.model.SolicitudActivacion;
import com.system.agb.SystemAnimalCoupB.repository.SolicitudActivacionRepository;
import com.system.agb.SystemAnimalCoupB.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudActivacionServiceImpl extends GenericServiceImpl<SolicitudActivacion, Integer> implements SolicitudActivacionService{

    @Autowired
    private SolicitudActivacionRepository solicitudActivacionRepository;
    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    public CrudRepository<SolicitudActivacion, Integer> getDao() {
        return solicitudActivacionRepository;
    }

    @Override
    public SolicitudActivacion findBySolicitudActivacion(Integer id_cuenta) {
        return solicitudActivacionRepository.findBySolicitudActivacion(id_cuenta);
    }

    @Override
    public List<SolicitudActivacion> findBySolicitudActivacionTrue() {
        return solicitudActivacionRepository.findBySolicitudActivacionTrue();
    }

    @Override
    public SolicitudActivacion findBySolicitudActivacionTrueDate(Integer id_cuenta) {
        return solicitudActivacionRepository.findBySolicitudActivacionTrueDate(id_cuenta);
    }
}
