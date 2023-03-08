package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.RazaAnimal;
import com.system.agb.SystemAnimalCoupB.service.RazaAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class RazaAnimalController {
    @Autowired
    RazaAnimalService razaAnimalService;

    @GetMapping("/raza/listarrazas")
    public ResponseEntity<List<RazaAnimal>> obtenerLista() {
        return new ResponseEntity<>(razaAnimalService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/raza/listarrazasFilter/{id}")
    public ResponseEntity<List<RazaAnimal>> getAllRazaOfAnimal(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(razaAnimalService.getAllRazaOfAnimal(id), HttpStatus.OK);
    }

    @PostMapping("/raza/crearraza")
    public ResponseEntity<RazaAnimal> crear( @RequestBody RazaAnimal razaAnimal){
        return new ResponseEntity<>(razaAnimalService.save(razaAnimal), HttpStatus.CREATED);
    }

    @DeleteMapping("/raza/eliminarraza/{id_razaanimal}")
    public void eliminar(@PathVariable("id_razaanimal") Integer id_razaanimal){
        razaAnimalService.delete(id_razaanimal);
    }

    @PutMapping("/raza/actualizarraza/{id_razaanimal}")
    public ResponseEntity<RazaAnimal> actualizar(@RequestBody RazaAnimal p,@PathVariable Integer id_razaanimal){
        RazaAnimal animaln = razaAnimalService.findById(id_razaanimal);
        animaln.setNombreraza(p.getNombreraza());
        animaln.setDescripcion(p.getDescripcion());
        return new ResponseEntity<>(razaAnimalService.save(animaln), HttpStatus.CREATED);
    }

    @GetMapping("/raza/buscarraza/{id_razaanimal}")
    public ResponseEntity<RazaAnimal> buscar(@PathVariable("id_razaanimal") Integer id_razaanimal) {
        return new ResponseEntity<>(razaAnimalService.findById(id_razaanimal), HttpStatus.OK);
    }

}

