package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.Cuenta;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaService extends  GenericService<Cuenta, Integer>{

    public Cuenta getUserAccount(String user, String password);

    public Cuenta findByUsuario(String user);

    public List<Cuenta> findAccountsByUserDiferentAdmin();

    public Cuenta findByPersonaAccount(String id_persona);

    public Cuenta findByUserIdAccountChat(Integer id_persona);
}
