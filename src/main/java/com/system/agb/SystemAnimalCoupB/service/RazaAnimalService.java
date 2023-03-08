package com.system.agb.SystemAnimalCoupB.service;


import com.system.agb.SystemAnimalCoupB.model.RazaAnimal;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RazaAnimalService extends  GenericService<RazaAnimal, Integer>{

public List<RazaAnimal> getAllRazaOfAnimal(Integer id);

    List<RazaAnimal> findAllById(List<Integer> razasId);
}
