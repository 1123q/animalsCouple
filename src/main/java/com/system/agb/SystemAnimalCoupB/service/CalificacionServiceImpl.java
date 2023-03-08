package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Calificacion;
import com.system.agb.SystemAnimalCoupB.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class CalificacionServiceImpl extends GenericServiceImpl<Calificacion, Integer> implements CalificacionService{

        @Autowired
        private CalificacionRepository calificacionRepository;

        @Override
        public CrudRepository<Calificacion, Integer> getDao() {
            return calificacionRepository;
        }
}
