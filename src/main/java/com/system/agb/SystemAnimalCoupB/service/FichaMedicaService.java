package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.FichaMedica;

public interface FichaMedicaService extends  GenericService<FichaMedica, Integer>{

    //void guardarFichaMedica(byte[] documento);
    public FichaMedica guardarFichaMedica(byte[] documento);

}

