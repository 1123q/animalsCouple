package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.model.Preferencia;
import com.system.agb.SystemAnimalCoupB.service.PersonaService;
import com.system.agb.SystemAnimalCoupB.service.EmailServiceImpl;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class emailcontroller {

    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    private PersonaService personaservice;


    @GetMapping("/email/send/{cedula}")
    public ResponseEntity<?> sendEmail(@PathVariable("cedula") String cedula){
        try {
            Persona pe= personaservice.findByCedula(cedula);
            if(pe != null) {
                if(emailService.sendEmail(pe)==true) {
                    return new ResponseEntity<>(pe, HttpStatus.OK);
                }else {
                    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INCONVENIENTE");
                }
            }else {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND");
            }
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR DE SERVIDOR");
        }

    }

    @GetMapping("/email/send/welcome/{correo}/{nombres}")
    public ResponseEntity<?>sendWelcomeAnimalCuople(@PathVariable("correo") String correo, @PathVariable("nombres") String nombres) {

        if( emailService.sendEmailWelcome(correo, nombres) == true) {
            return  ResponseEntity.status(HttpStatus.OK).body("Welcome to animalscouple.");
        }else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INCONVENIENTE");
        }

    }

                                                                                                      //String correo_notifica, String tipo_animal, String raza_notifica, String name_emisor, String mame_receptor
    @GetMapping("/email/sendmessageofUser/preferences/{id_tipo}/{id_persona_emisora}/{tipo_animal}/{raza_notifica}/{name_emisor}")
    public ResponseEntity<?>sendEmailofUserPreferences(@PathVariable("id_tipo") Integer id_tipo, @PathVariable("id_persona_emisora") Integer id_persona_emisora, @PathVariable("tipo_animal") String tipo_animal, @PathVariable("raza_notifica") String raza_notifica, @PathVariable("name_emisor") String name_emisor) {
//String correo_notifica, String tipo_animal, String raza_notifica, String name_emisor, String mame_receptor
        List<Persona> list_correoNotifica = personaservice.findByCorreoNotificaAndTipoAnimal(id_tipo);
        if (list_correoNotifica != null) {
            try {
                for (Persona persona : list_correoNotifica) {
                    if (persona.getId_persona() != id_persona_emisora) {
                        emailService.sendEmailNotificaUsernewAniaml(persona.getCorreo(), tipo_animal, raza_notifica, name_emisor, persona.getNombres());
                    }
                }
                return new ResponseEntity<>(HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    //servico para vericar que no llega el correo al que no tiene prendido las notificasiones.
    //SERVICIOQUE QUE VERIFICA QUE NO LLEGA LAS NOTIFICASIONES
    //SERVICIO QUE NO ESTA EN USO POR EL MOMENTOS.
    @GetMapping("/find/email/{id_tipo}")
    public ResponseEntity<List<Persona>>ver_personas(@PathVariable("id_tipo") Integer id_tipo) {
        return ResponseEntity.ok(personaservice.findByCorreoNotificaAndTipoAnimal(id_tipo));
    }
    //Fin del correo que le llega las notificasiones..

    @GetMapping("/email/send/activation/account/{cedula}")
    public ResponseEntity<?>sendEmailActivationAccount(@PathVariable("cedula") String cedula) {
        Persona p = personaservice.findByCedula(cedula);
        try {
            if(p != null){
                if( emailService.sendEmailActivationAccount(p.getCorreo()) == true) {
                    return  ResponseEntity.status(HttpStatus.OK).body("Solicitud de activacion para el usuario successful");
                }else {
                    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERR_SERVER-SEND-EMAIL");

                }
            }else{
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encoontrado");

            }
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INCONVENIENTE EN EL SERVIDOR AL ENVIAR CORREO");

        }

    }
}
