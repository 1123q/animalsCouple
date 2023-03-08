package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.Cuenta;
import com.system.agb.SystemAnimalCoupB.model.SolicitudActivacion;
import com.system.agb.SystemAnimalCoupB.service.CuentaService;
import com.system.agb.SystemAnimalCoupB.service.SolicitudActivacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class SolicitudActivacionController {
    @Autowired
    private SolicitudActivacionService solicitudActivacionService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/solicitud/ativacion/listarsolicitudesdActivacion")
    public ResponseEntity<List<SolicitudActivacion>> getSolicitudesActivacion() {
        try {
            return new ResponseEntity<>(solicitudActivacionService.findBySolicitudActivacionTrue(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/solicitud/ativacion/solicitarActivacion/{cedula}")
    public ResponseEntity<?> saveSolicitudesActivacion(@PathVariable("cedula") String cedula) {
        try {
            Cuenta c = cuentaService.findByPersonaAccount(cedula);
            if(c != null){
                if(c.isEstado() == true){
                    return new ResponseEntity<>("Le informamos que su cuenta se encuentra activa, no puede hacer la solicitud.", HttpStatus.BAD_REQUEST);
                }else {
                    SolicitudActivacion sa = solicitudActivacionService.findBySolicitudActivacionTrueDate(c.getId_cuenta());
                    if (sa != null) {

                        if (sa.getEstado() == false) {

                            sa.setEstado(sa.getEstado());
                            sa.setFecha(LocalDateTime.now());
                            sa.setCuenta(sa.getCuenta());
                            return new ResponseEntity<>(solicitudActivacionService.save(sa), HttpStatus.OK);
                        } else {
                            SolicitudActivacion solicitudActivacion = new SolicitudActivacion();
                            solicitudActivacion.setFecha(LocalDateTime.now());
                            solicitudActivacion.setEstado(false);
                            solicitudActivacion.setCuenta(c);
                            return new ResponseEntity<>(solicitudActivacionService.save(solicitudActivacion), HttpStatus.CREATED);
                        }
                    } else {
                        SolicitudActivacion solicitudActivacion = new SolicitudActivacion();
                        solicitudActivacion.setFecha(LocalDateTime.now());
                        solicitudActivacion.setEstado(false);
                        solicitudActivacion.setCuenta(c);
                        return new ResponseEntity<>(solicitudActivacionService.save(solicitudActivacion), HttpStatus.CREATED);
                    }
                }


            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getCause(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/solicitud/ativacion/updatesolicitud/accepSolicitude/{id_solicituda}")
    public ResponseEntity<SolicitudActivacion> updateSolicitudesActivacion(@RequestBody SolicitudActivacion solicitudActivacion, @PathVariable("id_solicituda") Integer id_solicituda) {
        try {
            SolicitudActivacion solicitudAcUp = solicitudActivacionService.findById(id_solicituda);
            solicitudAcUp.setEstado(solicitudActivacion.getEstado());
            solicitudAcUp.setFecha(solicitudActivacion.getFecha());
            solicitudAcUp.setCuenta(solicitudActivacion.getCuenta());
            return new ResponseEntity<>(solicitudActivacionService.save(solicitudAcUp), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
