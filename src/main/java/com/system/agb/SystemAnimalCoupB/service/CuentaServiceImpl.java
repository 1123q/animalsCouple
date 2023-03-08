package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Cuenta;
import com.system.agb.SystemAnimalCoupB.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CuentaServiceImpl extends GenericServiceImpl<Cuenta, Integer> implements CuentaService{
        @Autowired
        private CuentaRepository cuentaRepository;

        @Override
        public CrudRepository<Cuenta, Integer> getDao() {
            return cuentaRepository;
        }

        @Override
        public Cuenta getUserAccount(String user, String password) {
                return cuentaRepository.findByUsuarioAndContrasenia(user, password);
        }

        @Override
        public Cuenta findByUsuario(String user) {
                return cuentaRepository.findByUsuario(user);

        }

        @Override
        public List<Cuenta> findAccountsByUserDiferentAdmin(){
                return cuentaRepository.findAccountsByUserDiferentAdmin();
        }

        @Override
        public Cuenta findByPersonaAccount(String id_persona) {
                return cuentaRepository.findByPersonaAccount(id_persona);
        }

        @Override
        public Cuenta findByUserIdAccountChat(Integer id_persona) {
                return cuentaRepository.findByUserIdAccountChat(id_persona);
        }


}
