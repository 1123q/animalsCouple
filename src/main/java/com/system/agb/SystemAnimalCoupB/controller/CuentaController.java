package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.Cuenta;
import com.system.agb.SystemAnimalCoupB.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/cuentauser/listarcuenta")
    public ResponseEntity<List<Cuenta>> obtenerLista() {
        try {
            return new ResponseEntity<>(cuentaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/cuentauser/listarcuenta/diferentAdmin")
    public ResponseEntity<List<Cuenta>> obtenerListaAcountOfUserDiferentAdmin() {
        try {
            return new ResponseEntity<>(cuentaService.findAccountsByUserDiferentAdmin(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Implementación del metodo que me permita usar la comprovación de la contraseña.
    @GetMapping("/cuentauser/listarPass")
    public ResponseEntity<?> getPassword() {
        try {
            return new ResponseEntity<>(cuentaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Metódo que esta funcional..
    @PostMapping("/cuentauser/crearcuenta")
    public ResponseEntity<Cuenta> crear(@RequestBody Cuenta cuenta){
        try {
            cuenta.setEstado(true);
            return new ResponseEntity<>(cuentaService.save(cuenta), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cuentauser/eliminarcuenta/{id_cuenta}")
    public void eliminar(@PathVariable("id_cuenta") Integer id_cuenta){
        cuentaService.delete(id_cuenta);
    }

    @PutMapping("/cuentauser/actualizcuenta/{id_cuenta}")
    public ResponseEntity<Cuenta> actualizar(@RequestBody Cuenta p,@PathVariable Integer id_cuenta){
        Cuenta cuentaUp = cuentaService.findById(id_cuenta);
        if(cuentaUp != null){
            cuentaUp.setUsuario(p.getUsuario());
            cuentaUp.setContrasenia(p.getContrasenia());
            cuentaUp.setEstado(p.isEstado());
            cuentaUp.setRol(p.getRol());
            cuentaUp.setPersona(p.getPersona());
            return new ResponseEntity<>(cuentaService.save(cuentaUp), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cuentauser/buscarcuenta/{id_cuenta}")
    public ResponseEntity<Cuenta> buscar(@PathVariable("id_cuenta") Integer id_cuenta) {
        try {
            return new ResponseEntity<>(cuentaService.findById(id_cuenta), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/cuentauser/buscarcuenta/findby/{id_persona}")
    public ResponseEntity<Cuenta> findByIdAccount(@PathVariable("id_persona") Integer id_persona) {
        try {
            return new ResponseEntity<>(cuentaService.findByUserIdAccountChat(id_persona), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

