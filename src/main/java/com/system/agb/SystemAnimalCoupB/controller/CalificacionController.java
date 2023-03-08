package com.system.agb.SystemAnimalCoupB.controller;



import com.system.agb.SystemAnimalCoupB.model.Calificacion;
import com.system.agb.SystemAnimalCoupB.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class CalificacionController {
    @Autowired
    CalificacionService calificacionService;

    @GetMapping("/calificacion_animal/listarcalificaciones")
    public ResponseEntity<List<Calificacion>> obtenerLista() {
        return new ResponseEntity<>(calificacionService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/calificacion_animal/crearcalificacion")
    public ResponseEntity<Calificacion> crear( @RequestBody Calificacion calificacion){
        return new ResponseEntity<>(calificacionService.save(calificacion), HttpStatus.CREATED);
    }

    @DeleteMapping("/calificacion_animal/eliminarcalificacion/{id_calificacion}")
    public void eliminar(@PathVariable("id_calificacion") Integer id_calificacion){
        calificacionService.delete(id_calificacion);
    }

    @PutMapping("/calificacion_animal/actualizarcalificacion/{id_calificacion}")
    public ResponseEntity<Calificacion> actualizar(@RequestBody Calificacion p,@PathVariable Integer id_calificacion){
        Calificacion animaln = calificacionService.findById(id_calificacion);
        animaln.setNumsolicitudes(p.getNumsolicitudes());
        return new ResponseEntity<>(calificacionService.save(animaln), HttpStatus.CREATED);
    }

    @GetMapping("/calificacion_animal/buscaranimal/{id_calificacion}")
    public ResponseEntity<Calificacion> buscar(@PathVariable("id_calificacion") Integer id_calificacion) {
        return new ResponseEntity<>(calificacionService.findById(id_calificacion), HttpStatus.OK);
    }

}

