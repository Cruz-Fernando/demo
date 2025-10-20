package com.example.demo.controllers;

import com.example.demo.models.Etiqueta;
import com.example.demo.repositories.EtiquetaRepository;
import com.example.demo.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controlador REST para la entidad Etiqueta.
 * Gestiona operaciones CRUD y consultas personalizadas sobre las etiquetas.
 * 
 * @author 
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    // GET: Obtener todas las etiquetas
    @GetMapping
    public List<Etiqueta> getAllEtiquetas() {
        return etiquetaRepository.findAll();
    }

    // GET: Obtener etiqueta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> getEtiquetaById(@PathVariable Integer id) {
        Etiqueta etiqueta = etiquetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Etiqueta no encontrada para el id :: " + id));
        return ResponseEntity.ok(etiqueta);
    }

    // POST: Crear nueva etiqueta
    @PostMapping
    public Etiqueta createEtiqueta(@RequestBody Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    // PUT: Actualizar etiqueta existente
    @PutMapping("/{id}")
    public ResponseEntity<Etiqueta> updateEtiqueta(@PathVariable Integer id, @RequestBody Etiqueta detalles) {
        Etiqueta etiqueta = etiquetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Etiqueta no encontrada para el id :: " + id));

        etiqueta.setNombre(detalles.getNombre());
        etiqueta.setColor(detalles.getColor());

        final Etiqueta etiquetaActualizada = etiquetaRepository.save(etiqueta);
        return ResponseEntity.ok(etiquetaActualizada);
    }

    // DELETE: Eliminar etiqueta
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEtiqueta(@PathVariable Integer id) {
        Etiqueta etiqueta = etiquetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Etiqueta no encontrada para el id :: " + id));

        etiquetaRepository.delete(etiqueta);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminada", Boolean.TRUE);
        return response;
    }

    // GET: Buscar etiquetas por nombre
    @GetMapping("/buscar/{nombre}")
    public List<Etiqueta> buscarPorNombre(@PathVariable String nombre) {
        return etiquetaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // GET: Obtener etiquetas por color
    @GetMapping("/color/{color}")
    public List<Etiqueta> getEtiquetasPorColor(@PathVariable String color) {
        return etiquetaRepository.findByColor(color);
    }
}
