package com.example.demo.controllers;

import com.example.demo.models.Carpeta;
import com.example.demo.repositories.CarpetaRepository;
import com.example.demo.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controlador REST para la entidad Carpeta.
 * Administra las carpetas creadas por los usuarios.
 */
@RestController
@RequestMapping("/api/v1/carpetas")
public class CarpetaController {

    @Autowired
    private CarpetaRepository carpetaRepository;

    @GetMapping
    public List<Carpeta> getAllCarpetas() {
        return carpetaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carpeta> getCarpetaById(@PathVariable Integer id) {
        Carpeta carpeta = carpetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carpeta no encontrada con id :: " + id));
        return ResponseEntity.ok(carpeta);
    }

    @PostMapping
    public Carpeta createCarpeta(@RequestBody Carpeta carpeta) {
        carpeta.setFechaCreacion(java.time.LocalDateTime.now());
        carpeta.setFechaModificacion(java.time.LocalDateTime.now());
        return carpetaRepository.save(carpeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carpeta> updateCarpeta(@PathVariable Integer id, @RequestBody Carpeta detalles) {
        Carpeta carpeta = carpetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carpeta no encontrada con id :: " + id));

        carpeta.setNombre(detalles.getNombre());
        carpeta.setDescripcion(detalles.getDescripcion());
        carpeta.setColor(detalles.getColor());
        carpeta.setFechaModificacion(java.time.LocalDateTime.now());

        final Carpeta actualizada = carpetaRepository.save(carpeta);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCarpeta(@PathVariable Integer id) {
        Carpeta carpeta = carpetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carpeta no encontrada con id :: " + id));

        carpetaRepository.delete(carpeta);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminada", Boolean.TRUE);
        return response;
    }
}
