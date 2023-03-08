package com.system.agb.SystemAnimalCoupB.controller;

import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.model.Preferencia;
import com.system.agb.SystemAnimalCoupB.model.RazaAnimal;
import com.system.agb.SystemAnimalCoupB.service.PersonaService;
import com.system.agb.SystemAnimalCoupB.service.PreferenceService;
import com.system.agb.SystemAnimalCoupB.service.RazaAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class PreferenceController {
    @Autowired
    private PreferenceService preferenceService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private RazaAnimalService razaAnimalService;

    @PostMapping("/preference/{id_persona}/save")
    public ResponseEntity<?> savePreferencesPerson(@PathVariable("id_persona") Integer id_persona, @RequestBody List<Integer> razasId) {
   // public ResponseEntity<?> savePreferencesPerson(@PathVariable("id_persona") Integer id_persona) {
       // List<Integer> listaIds = Arrays.asList(1, 2, 3);

        try {
            Persona persona = personaService.findById(id_persona);
            List<RazaAnimal> listRaza = razaAnimalService.findAllById(razasId);
            if(persona != null && listRaza != null){
                for(RazaAnimal raza: listRaza){
                    Preferencia preferencia = new Preferencia();
                    preferencia.setEstado(true);
                    preferencia.setPersona(persona);
                    preferencia.setRazaAnimal(raza);

                    preferenceService.save(preferencia);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/preference/getAllPreferences")
    public ResponseEntity<List<Preferencia>> getAllPreferences() {
        return new ResponseEntity<>(preferenceService.findByAll(), HttpStatus.OK);
    }


    @GetMapping("/preference/getAllPreferences/{id_persona}")
    public ResponseEntity<List<Preferencia>> getAllPreferencesByUser(@PathVariable("id_persona") Integer id_persona) {
        try {
            Persona persona = personaService.findById(id_persona);
            List<Preferencia> currentPreferences = preferenceService.findAllByPersona(persona);
            return new ResponseEntity<>(currentPreferences, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping("/preference/{id_persona}/update")
    public ResponseEntity<?> updatePreference(@PathVariable("id_persona") Integer id_persona, @RequestBody List<Integer> razasId) {
    //public ResponseEntity<?> updatePreference(@PathVariable("id_persona") Integer id_persona) {

       // List<Integer> listaIds = Arrays.asList(3,1);
        try {
        Persona persona = personaService.findById(id_persona);
        List<RazaAnimal> listRaza = razaAnimalService.findAllById(razasId);
        List<Preferencia> currentPreferences = preferenceService.findAllByPersona(persona);
            if (persona != null && listRaza != null) {
                for (RazaAnimal raza : listRaza) {
                    boolean exists = false;
                    for (Preferencia pref : currentPreferences) {
                        if (pref.getRazaAnimal().getId_razaanimal().equals(raza.getId_razaanimal())) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        Preferencia preferencia = new Preferencia();
                        preferencia.setEstado(true);
                        preferencia.setPersona(persona);
                        preferencia.setRazaAnimal(raza);
                        preferenceService.save(preferencia);
                    }
                }
                for (Preferencia pref : currentPreferences) {
                    boolean exists = false;
                    for (RazaAnimal raza : listRaza) {
                        if (pref.getRazaAnimal().getId_razaanimal().equals(raza.getId_razaanimal())) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {

                        preferenceService.deleteEntity(pref);
                    }
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/preference/filter/findBytipoandUserLoggin/{id_person}")
    public ResponseEntity<List<Object[]>> findByTipoByUserLoggin(@PathVariable("id_person") Integer id_person) {
        return new ResponseEntity<>(preferenceService.findDistinctAnimalTypesByUser(id_person), HttpStatus.OK);
    }

    @GetMapping("/preference/filter/findByrazazDependenceOfTipoAndUserLoggin/{id_person}/{id_tipo}")
    public ResponseEntity<List<Object[]>> findByrazazDependenceOfTipoAndUserLoggin(@PathVariable("id_person") Integer id_person, @PathVariable("id_tipo") Integer id_tipo) {
        return new ResponseEntity<>(preferenceService.findDistinctAnimalTypesByUserAndTiporaza(id_person, id_tipo), HttpStatus.OK);
    }

    @GetMapping("/preference/filter/findMisPreferenciasbypersona/{id_person}")
    public ResponseEntity<List<Animal>> findmispreferencias(@PathVariable("id_person") Integer id_person) {
        return new ResponseEntity<>(preferenceService.findmispreferencias(id_person), HttpStatus.OK);
    }
}
