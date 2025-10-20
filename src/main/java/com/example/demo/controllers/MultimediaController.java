package com.example.demo.controllers;

import com.example.demo.models.Multimedia;
import com.example.demo.repositories.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de archivos multimedia asociados a notas.
 */
@RestController
@RequestMapping("/api/multimedia")
public class MultimediaController {

    @Autowired
    private MultimediaRepository multimediaRepository;

    // Obtener todos los archivos multimedia
    @GetMapping
    public List<Multimedia> getAllMultimedia() {
        return multimediaRepository.findAll();
    }

    // Obtener multimedia por ID
    @GetMapping("/{id}")
    public Optional<Multimedia> getMultimediaById(@PathVariable Integer id) {
        return multimediaRepository.findById(id);
    }

    // Obtener multimedia por nota
    @GetMapping("/nota/{notaId}")
    public List<Multimedia> getMultimediaByNota(@PathVariable Integer notaId) {
        return multimediaRepository.findByNotaId(notaId);
    }

    // Subir nuevo archivo
    @PostMapping
    public Multimedia uploadMultimedia(@RequestBody Multimedia multimedia) {
        multimedia.setFechaSubida(java.time.LocalDateTime.now());
        return multimediaRepository.save(multimedia);
    }

    // Actualizar multimedia existente
    @PutMapping("/{id}")
    public Multimedia updateMultimedia(@PathVariable Integer id, @RequestBody Multimedia nuevoArchivo) {
        return multimediaRepository.findById(id).map(m -> {
            m.setNombreArchivo(nuevoArchivo.getNombreArchivo());
            m.setTipoArchivo(nuevoArchivo.getTipoArchivo());
            m.setUrlArchivo(nuevoArchivo.getUrlArchivo());
            m.setTamaño(nuevoArchivo.getTamaño());
            return multimediaRepository.save(m);
        }).orElseGet(() -> {
            nuevoArchivo.setId(id);
            return multimediaRepository.save(nuevoArchivo);
        });
    }

    // Eliminar archivo
    @DeleteMapping("/{id}")
    public void deleteMultimedia(@PathVariable Integer id) {
        multimediaRepository.deleteById(id);
    }
}
