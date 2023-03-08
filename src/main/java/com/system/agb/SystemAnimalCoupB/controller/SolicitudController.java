package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.Solicitud;
import com.system.agb.SystemAnimalCoupB.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class SolicitudController {
    @Autowired
    SolicitudService solicitudService;

    @GetMapping("/solicitud/listarsolicitudes")
    public ResponseEntity<List<Solicitud>> obtenerLista() {
        return new ResponseEntity<>(solicitudService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/solicitud/crearsolicitud")
    public ResponseEntity<Solicitud> crear( @RequestBody Solicitud solicitud){
        solicitud.setEstado("Pendiente");
        solicitud.setFecha(LocalDateTime.now());
        return new ResponseEntity<>(solicitudService.save(solicitud), HttpStatus.CREATED);
    }

    @DeleteMapping("/solicitud/eliminarsolicitud/{id_solicitud}")
    public void eliminar(@PathVariable("id_solicitud") Integer id_solicitud){
        solicitudService.delete(id_solicitud);
    }

    @PutMapping("/solicitud/actualizarsolicitud/{id_solicitud}")
    public ResponseEntity<Solicitud> actualizar(@RequestBody Solicitud p,@PathVariable Integer id_solicitud){
        Solicitud animaln = solicitudService.findById(id_solicitud);
        animaln.setFecha(p.getFecha());
        animaln.setTipopago(p.getTipopago());
        animaln.setComentario(p.getComentario());
        animaln.setEstado(p.getEstado());
        return new ResponseEntity<>(solicitudService.save(animaln), HttpStatus.CREATED);
    }

    @GetMapping("/solicitud/buscaranimal/{id_solicitud}")
    public ResponseEntity<Solicitud> buscar(@PathVariable("id_solicitud") Integer id_solicitud) {
        return new ResponseEntity<>(solicitudService.findById(id_solicitud), HttpStatus.OK);
    }

    //edi

    //listar por id del animal
    @GetMapping("/solicitud/buscarsolicitudporpersona/{estado}/{id_persona}")
    public ResponseEntity<List<Solicitud>> getAllofpersona(@PathVariable("estado") String estado, @PathVariable("id_persona") Integer id_persona) {
        try {
            return new ResponseEntity<>(solicitudService.findSolicitudesPendientesByPersona(estado, id_persona), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/solicitud/buscarsolicitudesporpersona/{estado}/{id_persona}")
    public ResponseEntity<List<Solicitud>> getAllofEspersona(@PathVariable("estado") String estado, @PathVariable("id_persona") Integer id_persona) {
        try {
            return new ResponseEntity<>(solicitudService.findSolicitudeByEsPersona(estado, id_persona), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

