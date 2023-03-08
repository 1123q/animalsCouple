package com.system.agb.SystemAnimalCoupB.controller;



import com.system.agb.SystemAnimalCoupB.model.FichaMedica;
import com.system.agb.SystemAnimalCoupB.repository.FichaMedicaRepository;
import com.system.agb.SystemAnimalCoupB.service.FichaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class FichaMedicaController {
    @Autowired
    FichaMedicaService fichaMedicaService;
    @Autowired
    private FichaMedicaRepository fichaMedicaRepository;

    @GetMapping("/ficha/listarfichasMedicas")
    public ResponseEntity<List<FichaMedica>> obtenerLista() {
        return new ResponseEntity<>(fichaMedicaService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/ficha/crearfichaMedica")
    public ResponseEntity<FichaMedica> crear( @RequestBody FichaMedica fichaMedica){
        return new ResponseEntity<>(fichaMedicaService.save(fichaMedica), HttpStatus.CREATED);
    }

    /*@PostMapping("/ficha/crear")
    public ResponseEntity<FichaMedica> crear(@RequestParam("file") MultipartFile file, @RequestBody FichaMedica fichaMedica) throws IOException {
        fichaMedica.setDocumento(file.getBytes()); // Guarda el archivo PDF en el objeto FichaMedica como un array de bytes

        FichaMedica fichaMedicaGuardada = fichaMedicaService.save(fichaMedica); // Guarda la ficha médica en la base de datos

        return new ResponseEntity<>(fichaMedicaGuardada, HttpStatus.CREATED);
    }
*/
    @PostMapping("/ficha")
    public ResponseEntity<?> guardarFichaMedica(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ha proporcionado ningún archivo.");
        }

        byte[] bytesDocumento = file.getBytes();

        return new ResponseEntity<>(fichaMedicaService.guardarFichaMedica(bytesDocumento), HttpStatus.CREATED);
    }

    @DeleteMapping("/ficha/eliminarfichaMedica/{id_fichaMedica}")
    public void eliminar(@PathVariable("id_fichaMedica") Integer id_fichaMedica){
        fichaMedicaService.delete(id_fichaMedica);
    }

    @PutMapping("/ficha/actualizarfichaMedica/{id_fichaMedica}")
    public ResponseEntity<FichaMedica> actualizar(@RequestBody FichaMedica p,@PathVariable Integer id_fichaMedica){
        FichaMedica animaln = fichaMedicaService.findById(id_fichaMedica);
        animaln.setDocumento(p.getDocumento());
        return new ResponseEntity<>(fichaMedicaService.save(animaln), HttpStatus.CREATED);
    }

    @GetMapping("/ficha/buscarfichaMedica/{id_fichaMedica}")
    public ResponseEntity<FichaMedica> buscar(@PathVariable("id_fichaMedica") Integer id_fichaMedica) {
        return new ResponseEntity<>(fichaMedicaService  .findById(id_fichaMedica), HttpStatus.OK);
    }

}

