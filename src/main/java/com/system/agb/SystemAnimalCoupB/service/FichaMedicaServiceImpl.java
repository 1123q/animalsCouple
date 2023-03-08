package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.FichaMedica;
import com.system.agb.SystemAnimalCoupB.repository.FichaMedicaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FichaMedicaServiceImpl extends GenericServiceImpl<FichaMedica, Integer> implements FichaMedicaService{

        @Autowired
        private FichaMedicaRepository fichaMedicaRepository;

        @Override
        public CrudRepository<FichaMedica, Integer> getDao() {
            return fichaMedicaRepository;
        }

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        @Transactional

        public FichaMedica guardarFichaMedica(byte[] documento) {
                FichaMedica fichaMedica = new FichaMedica();
                fichaMedica.setDocumento(documento);
               return  fichaMedicaRepository.save(fichaMedica);
                //entityManager.persist(fichaMedica);
        }


        /*public void guardarFichaMedica(byte[] documento) {
                FichaMedica fichaMedica = new FichaMedica();
                fichaMedica.setDocumento(documento);
                entityManager.persist(fichaMedica);
        }*/
}
