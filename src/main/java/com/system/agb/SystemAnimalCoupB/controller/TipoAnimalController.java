package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.TipoAnimal;
import com.system.agb.SystemAnimalCoupB.repository.TipoAnimalRepository;
import com.system.agb.SystemAnimalCoupB.service.TipoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class TipoAnimalController {
    @Autowired
    TipoAnimalService tipoAnimalService;

    @GetMapping("/tipoanimal/listartiposAnimales")
    public ResponseEntity<List<TipoAnimal>> obtenerLista() {
        return new ResponseEntity<>(tipoAnimalService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/tipoanimal/creartipoAnimal")
    public ResponseEntity<TipoAnimal> crear( @RequestBody TipoAnimal tipoAnimal){
        return new ResponseEntity<>(tipoAnimalService.save(tipoAnimal), HttpStatus.CREATED);
    }

    @DeleteMapping("/tipoanimal/eliminartipoAnimal/{id_tipoAnimal}")
    public void eliminar(@PathVariable("id_tipoAnimal") Integer id_tipoAnimal){
        tipoAnimalService.delete(id_tipoAnimal);
    }

    @PutMapping("/tipoanimal/actualizartipoAnimal/{id_tipoAnimal}")
    public ResponseEntity<TipoAnimal> actualizar(@RequestBody TipoAnimal p,@PathVariable Integer id_tipoAnimal){
        TipoAnimal animaln = tipoAnimalService.findById(id_tipoAnimal);
        animaln.setNombretipo(p.getNombretipo());
        animaln.setDescripcion(p.getDescripcion());
        animaln.setFoto(p.getFoto());
        return new ResponseEntity<>(tipoAnimalService.save(animaln), HttpStatus.CREATED);
    }

    @GetMapping("/tipoanimal/buscartipoAnimal/{id_tipoAnimal}")
    public ResponseEntity<TipoAnimal> buscar(@PathVariable("id_tipoAnimal") Integer id_tipoAnimal) {
        return new ResponseEntity<>(tipoAnimalService.findById(id_tipoAnimal), HttpStatus.OK);
    }

    @Autowired
    private TipoAnimalRepository tipoAnimalRepository;

    @GetMapping
    public List<TipoAnimal> getAllTiposWithRazas() {
        return tipoAnimalRepository.findAllWithRazas();
    }
}

