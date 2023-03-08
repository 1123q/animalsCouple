package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.service.EmailServiceImpl;
import com.system.agb.SystemAnimalCoupB.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/persona/listarpersonas")
    public ResponseEntity<List<Persona>> obtenerLista() {
        return new ResponseEntity<>(personaService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/persona/crearpersona")
    public ResponseEntity<Persona> crear( @RequestBody Persona persona){
        try {
            return new ResponseEntity<>(personaService.save(persona), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/persona/eliminarpersona/{id_persona}")
    public void eliminar(@PathVariable("id_persona") Integer id_persona){
        personaService.delete(id_persona);
    }

    @PutMapping("/persona/actualizarpersona/{id_persona}")
    public ResponseEntity<Persona> actualizar(@RequestBody Persona p,@PathVariable Integer id_persona){
        Persona person = personaService.findById(id_persona);
        person.setCedula(p.getCedula());
        person.setNombres(p.getNombres());
        person.setEdad(p.getEdad());
        person.setGenero(p.getGenero());
        person.setDireccion(p.getDireccion());
        person.setTelefono(p.getTelefono());
        person.setCelular(p.getCelular());
        person.setCorreo(p.getCorreo());
        person.setCorreo_notifica(p.getCorreo_notifica());
        person.setFoto(p.getFoto());
        return new ResponseEntity<>(personaService.save(person), HttpStatus.CREATED);
    }

    @GetMapping("/persona/buscarpersona/{id_persona}")
    public ResponseEntity<Persona> buscar(@PathVariable("id_persona") Integer id_persona) {
        return new ResponseEntity<>(personaService.findById(id_persona), HttpStatus.OK);
    }

    //Actualizar foto de la persona en tiempo real..




    @PutMapping("/persona/updatepicture/{id}")
    public ResponseEntity<?> updatePucture(@PathVariable("id") Integer id, @RequestBody String foto){
        try {
            if(personaService.updatePicture(foto, id) == 1){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la fot"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/persona/filter/correoActivo/{id_tipo}")
    public ResponseEntity<List<Persona>> filtroCorreoelectronico(@PathVariable("id_tipo") Integer id_tipo) {
        return new ResponseEntity<>(personaService.findByCorreoNotificaAndTipoAnimal(id_tipo), HttpStatus.OK);
    }
}

