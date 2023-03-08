package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.Cuenta;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.repository.CuentaRepository;
import com.system.agb.SystemAnimalCoupB.service.CuentaService;
import com.system.agb.SystemAnimalCoupB.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class OAuthController {
    @Autowired
    private CuentaService cuentaOAuth;

    @Autowired
    private PersonaService createPerson;

   // @GetMapping("/signIn/getuser/{user}/{password}")
    //public ResponseEntity<Cuenta> getUser(@PathVariable("user") String user, @PathVariable("password") String password){
      //  return ResponseEntity.ok(cuentaOAuth.getUserAccount(user, password));
    //}

    /*@GetMapping("/signIn/getuser/{user}/{password}")
    public ResponseEntity<?> login(@PathVariable("user") String user, @PathVariable("password") String password) {
        try {
            Cuenta isPresent = cuentaOAuth.findByUsuario(user);
            if(isPresent != null){
                if(isPresent.getContrasenia().equals(password)){
                    return new ResponseEntity<>(isPresent, HttpStatus.OK);
                }else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La contraseña ingresada no es correcta.");
                }

            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario ingresado no esta registrado en el sistema.");
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }*/

    @GetMapping("/signIn/getuser/{user}/{password}")
    public ResponseEntity<?> login(@PathVariable("user") String user, @PathVariable("password") String password) {

        try {
            Cuenta isPresent = cuentaOAuth.findByUsuario(user);
            if(isPresent != null){
                if(isPresent.getContrasenia().equals(password)){
                    if(isPresent.isEstado()){
                        return new ResponseEntity<>(isPresent, HttpStatus.OK);
                    }else{
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("Su cuenta "+isPresent.getUsuario()+" esta inhabilitada, contactarse con soporte tecnico.");
                    }

                }else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La contraseña ingresada no es correcta.");
                }

            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario ingresado no esta registrado en el sistema.");
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/registerData/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody Persona persona){

        return ResponseEntity.status(HttpStatus.OK).body(createPerson.save(persona));
    }

    @PostMapping("/signUp/saveUser")
    public ResponseEntity<?> saveAccount(@RequestBody Cuenta cuenta){
        cuenta.setRol("USUARIO");
        cuenta.setEstado(true);
        return ResponseEntity.status(HttpStatus.OK).body(cuentaOAuth.save(cuenta));
    }

    @GetMapping("/oauth/existByUsername/{user}")
    public ResponseEntity<?> existByUsername(@PathVariable("user") String user){
        try {
            return new ResponseEntity<>(cuentaOAuth.findByUsuario(user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/oauth/existByCedula/{cedula}")
    public ResponseEntity<?> findByCedula(@PathVariable("cedula") String cedula){
        try {
                return new ResponseEntity<>(createPerson.findByCedula(cedula), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/oauth/existByCorreo/{correo}")
    public ResponseEntity<?> existByCorreo(@PathVariable("correo") String correo){
        try {

            return new ResponseEntity<>(createPerson.existsByCorreo(correo), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
