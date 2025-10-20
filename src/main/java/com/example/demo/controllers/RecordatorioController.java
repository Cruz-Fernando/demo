package com.example.demo.controllers;

import com.example.demo.models.Recordatorio;
import com.example.demo.repositories.RecordatorioRepository;
import com.example.demo.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/recordatorios")
public class RecordatorioController {

    @Autowired
    private RecordatorioRepository recordatorioRepository;

    @GetMapping
    public List<Recordatorio> getAllRecordatorios() {
        return recordatorioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recordatorio> getRecordatorioById(@PathVariable Integer id) {
        Recordatorio recordatorio = recordatorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recordatorio no encontrado para el id :: " + id));
        return ResponseEntity.ok(recordatorio);
    }

    @PostMapping
    public Recordatorio createRecordatorio(@RequestBody Recordatorio recordatorio) {
        return recordatorioRepository.save(recordatorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recordatorio> updateRecordatorio(@PathVariable Integer id,
            @RequestBody Recordatorio detalles) {
        Recordatorio recordatorio = recordatorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recordatorio no encontrado para el id :: " + id));

        recordatorio.setFechaHora(detalles.getFechaHora());
        recordatorio.setMensaje(detalles.getMensaje());
        recordatorio.setActivo(detalles.getActivo());

        final Recordatorio actualizado = recordatorioRepository.save(recordatorio);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRecordatorio(@PathVariable Integer id) {
        Recordatorio recordatorio = recordatorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recordatorio no encontrado para el id :: " + id));

        recordatorioRepository.delete(recordatorio);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }

    @GetMapping("/activos")
    public List<Recordatorio> getActivos() {
        return recordatorioRepository.findByActivoTrue();
    }
}
