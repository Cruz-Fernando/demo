package com.example.demo.controllers;

import com.example.demo.models.Perfil;
import com.example.demo.repositories.PerfilRepository;
import com.example.demo.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controlador REST para la entidad Perfil.
 * Administra la información de los perfiles de usuario.
 */
@RestController
@RequestMapping("/api/v1/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> getAllPerfiles() {
        return perfilRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getPerfilById(@PathVariable Integer id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id :: " + id));
        return ResponseEntity.ok(perfil);
    }

    @PostMapping
    public Perfil createPerfil(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> updatePerfil(@PathVariable Integer id, @RequestBody Perfil detalles) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id :: " + id));

        perfil.setNombreCompleto(detalles.getNombreCompleto());
        perfil.setBiografia(detalles.getBiografia());
        perfil.setFotoPerfil(detalles.getFotoPerfil());
        perfil.setTelefono(detalles.getTelefono());
        perfil.setFechaNacimiento(detalles.getFechaNacimiento());

        final Perfil actualizado = perfilRepository.save(perfil);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletePerfil(@PathVariable Integer id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id :: " + id));

        perfilRepository.delete(perfil);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
