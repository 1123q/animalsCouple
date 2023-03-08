package com.system.agb.SystemAnimalCoupB.controller;


import com.system.agb.SystemAnimalCoupB.model.Animal;
import com.system.agb.SystemAnimalCoupB.model.Persona;
import com.system.agb.SystemAnimalCoupB.model.RazaAnimal;
import com.system.agb.SystemAnimalCoupB.service.AnimalService;
import com.system.agb.SystemAnimalCoupB.service.PersonaService;
import com.system.agb.SystemAnimalCoupB.service.RazaAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = {"http://localhost:4200"})
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @Autowired
    private RazaAnimalService razaAnimalServiceAni;

    @Autowired
    private PersonaService personaService;

    @GetMapping("/animal/listaranimales")
    public ResponseEntity<List<Animal>> obtenerLista() {
        try {
            return new ResponseEntity<>(animalService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/animal/listperTip/{id_tipo}")
    public ResponseEntity<List<Animal>> getAllofTip(@PathVariable("id_tipo") Integer id_tipo) {
        try {
            return new ResponseEntity<>(animalService.findByTipoId(id_tipo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/animal/listperRaza/{id_raza}")
    public ResponseEntity<List<Animal>> getAllofRaza(@PathVariable("id_raza") Integer id_raza) {
        try {
            return new ResponseEntity<>(animalService.findByRazaId(id_raza), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/animal/crearanimal")
    public ResponseEntity<Animal> crear( @RequestBody Animal animal){
        try {
            animal.setEstado(true); // Siempre el ingreso del animla va ingresar como true
            return new ResponseEntity<>(animalService.save(animal), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @DeleteMapping("/animal/eliminaranimal/{id_animal}")
    public void eliminar(@PathVariable("id_animal") Integer id_animal){
        try {
            animalService.delete(id_animal);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @PutMapping("/animal/actualizaranimal/{id_animal}")
    public ResponseEntity<Animal> actualizar(@RequestBody Animal p, @PathVariable("id_animal") Integer id_animal){
        try {
            Animal animaln = animalService.findById(id_animal);
            if(animaln != null){
                animaln.setNombre(p.getNombre());
                animaln.setFoto(p.getFoto());
                animaln.setEdad(p.getEdad());
                animaln.setGenero(p.getGenero());
                animaln.setPeso(p.getPeso());
                animaln.setColor(p.getColor());
                animaln.setDisponibilidad(p.getDisponibilidad());
                animaln.setEstado(p.getEstado());
                animaln.setFichaMedica(p.getFichaMedica());
                return new ResponseEntity<>(animalService.save(animaln), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/animal/actualizaranimal/calificasion/{disponibilidad}/{id_animal}/{num_soli}")
    @ResponseStatus(HttpStatus.OK)
    public int updateAnimalOfCalification(@PathVariable("id_animal") Integer id_animal, @PathVariable("disponibilidad") Boolean disponibilidad, @PathVariable("num_soli") Integer num_soli) {
        return animalService.updateAnimalNumSoliAndDisponibilidadById(id_animal, num_soli, disponibilidad);
    }

    @GetMapping("/animal/buscaranimal/{id_animal}")
    public ResponseEntity<Animal> buscar(@PathVariable("id_animal") Integer id_animal) {
        try {
            return new ResponseEntity<>(animalService.findById(id_animal), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animal/buscaranimal/{id_animal}/{nombre}")
    public ResponseEntity<List<Animal>> findByLikeTipo(@PathVariable("id_animal") Integer id_animal, @PathVariable("nombre") String nombre) {
        try {
            return new ResponseEntity<>(animalService.findByTipoIdAndNombreLike(id_animal, nombre), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animal/findAll/filter/{nombre}")
    public ResponseEntity<List<Animal>> findAllAnimalsIdAndNombreLike(@PathVariable("nombre") String nombre) {
        try {
            return new ResponseEntity<>(animalService.findAllAnimalsIdAndNombreLike(nombre), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animal/findAll")
    public ResponseEntity<List<Animal>> findAllAnimals() {
        try {
            return new ResponseEntity<>(animalService.findAllAnimals(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animal/findByUser/{id_persona}")
    public ResponseEntity<List<Animal>> findByUser(@PathVariable("id_persona") Integer id_persona) {
        try {
            return new ResponseEntity<>(animalService.findByUser(id_persona), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //La ultima implementacion para la parte de los filtros en el caso del genero de los animales dependiendo si es macho o hembre
    @GetMapping("/animal/filter/raza/findByGenderAnimal/{id_raza}/{gender}")
    public ResponseEntity<List<Animal>> findByGenderAnimal(@PathVariable("id_raza") Integer id_raza, @PathVariable("gender") String gender) {
        try {
            return new ResponseEntity<>(animalService.findByRazaIdAndGenero(id_raza, gender), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animal/findBygenderAnimal/{id_gender}/{gender}")
    public ResponseEntity<List<Animal>> findByUser(@PathVariable("id_gender") Integer id_gender, @PathVariable("gender") String gender) {
        try {
            return new ResponseEntity<>(animalService.findByTipoIdAndGeneroLike(id_gender, gender), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Para el filtro general
    @PostMapping("/animal/findAllByRazasBytipo/{id_tipo}/getall")
    public ResponseEntity<?> findAllByRazasBytipo(@PathVariable("id_tipo") Integer id_tipo, @RequestBody List<Integer> razasId) {
        // public ResponseEntity<?> savePreferencesPerson(@PathVariable("id_persona") Integer id_persona) {
        // List<Integer> listaIds = Arrays.asList(1, 2, 3);

        //List<RazaAnimal> listRaza = razaAnimalServiceAni.findAllById(razasId);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(animalService.findAnimalesByRazaAndTipo(id_tipo, razasId));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animal/getalltipos/filterbyletter/tipo/{tipo}")
    public ResponseEntity<?> findByEstadoAndRaAnimal_RazaAnimal_NombretipoStartingWithIgnoreCase(@PathVariable("tipo") String tipo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(animalService.findByEstadoAndRaAnimal_RazaAnimal_NombretipoStartingWithIgnoreCase(tipo));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animal/getallanimals/update/status/desactiva/{id_persona}")
    public ResponseEntity<?> findAllByPersona(@PathVariable("id_persona") Integer id_persona){
        try {
            Persona p = personaService.findById(id_persona);
            if(p != null){
                List<Animal> listAnimal = animalService.findAnimalByPersona(p);
                try {
                    for (Animal animal : listAnimal) {
                        animalService.updateAnimalEstadoById(animal.getId_animal());
                    }
                    return new ResponseEntity<>(p, HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

