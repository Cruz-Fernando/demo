package com.example.demo.controllers;

import com.example.demo.models.Nota;
import com.example.demo.repositories.NotaRepository;
import com.example.demo.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controlador REST para la entidad Nota.
 * Gestiona operaciones CRUD y consultas avanzadas sobre las notas del usuario.
 * 
 * @author
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    // GET: Obtener todas las notas
    @GetMapping
    public List<Nota> getAllNotas() {
        return notaRepository.findAll();
    }

    // GET: Obtener nota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaById(@PathVariable Integer id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada para el id :: " + id));
        return ResponseEntity.ok(nota);
    }

    // POST: Crear nueva nota
    @PostMapping
    public Nota createNota(@RequestBody Nota nota) {
        nota.setFechaCreacion(java.time.LocalDateTime.now());
        return notaRepository.save(nota);
    }

    // PUT: Actualizar nota existente
    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable Integer id, @RequestBody Nota notaDetalles) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada para el id :: " + id));

        nota.setTitulo(notaDetalles.getTitulo());
        nota.setContenido(notaDetalles.getContenido());
        nota.setColorFondo(notaDetalles.getColorFondo());
        nota.setEsPublica(notaDetalles.isEsPublica());
        nota.setFechaModificacion(java.time.LocalDateTime.now());

        final Nota notaActualizada = notaRepository.save(nota);
        return ResponseEntity.ok(notaActualizada);
    }

    // DELETE: Eliminar una nota por ID
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteNota(@PathVariable Integer id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada para el id :: " + id));

        notaRepository.delete(nota);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminada", Boolean.TRUE);
        return response;
    }

    // GET: Buscar notas por título
    @GetMapping("/buscar/titulo/{titulo}")
    public List<Nota> getNotasByTitulo(@PathVariable String titulo) {
        return notaRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // GET: Buscar notas públicas
    @GetMapping("/publicas")
    public List<Nota> getNotasPublicas() {
        return notaRepository.findByEsPublicaTrue();
    }

    // GET: Buscar notas por etiqueta
    @GetMapping("/etiqueta/{nombreEtiqueta}")
    public List<Nota> getNotasPorEtiqueta(@PathVariable String nombreEtiqueta) {
        return notaRepository.findNotasByEtiqueta(nombreEtiqueta);
    }

    // GET: Buscar notas por fecha de creación
    @GetMapping("/fecha/{anio}/{mes}")
    public List<Nota> getNotasByFecha(@PathVariable int anio, @PathVariable int mes) {
        return notaRepository.findByFechaCreacionBetween(
                java.time.LocalDateTime.of(anio, mes, 1, 0, 0),
                java.time.LocalDateTime.of(anio, mes, 31, 23, 59));
    }

    // GET: Obtener estadísticas de notas por color de fondo
    @GetMapping("/estadisticas/colores")
    public List<Map<String, Object>> getNotasPorColor() {
        List<Object[]> resultados = notaRepository.countNotasByColorFondo();
        List<Map<String, Object>> estadisticas = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Map<String, Object> stat = new HashMap<>();
            stat.put("color", resultado[0]);
            stat.put("cantidad", resultado[1]);
            estadisticas.add(stat);
        }

        return estadisticas;
    }
}
