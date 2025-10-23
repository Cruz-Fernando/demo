package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad Nota
 * Representa una nota creada por el usuario.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private String colorFondo;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaModificacion = LocalDateTime.now();
    private boolean esPublica;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "carpeta_id")
    private Carpeta carpeta;

    @ManyToMany
    @JoinTable(name = "nota_etiquetas", joinColumns = @JoinColumn(name = "nota_id"), inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
    private List<Etiqueta> etiquetas;

    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Multimedia> archivos;

    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recordatorio> recordatorios;

    public void crearNota() {
    }

    public void editarNota() {
    }

    public void eliminarNota() {
    }

    public void cambiarColor() {
    }

    public void compartirNota() {
    }

    public void archivarNota() {
    }
}
