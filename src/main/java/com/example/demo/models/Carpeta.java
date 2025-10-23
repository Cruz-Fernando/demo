package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad Carpeta
 * Representa un contenedor de notas perteneciente a un usuario.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carpetas")
public class Carpeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private String color;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaModificacion = LocalDateTime.now();

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "carpeta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas;

    public void crearCarpeta() {
    }

    public void editarCarpeta() {
    }

    public void eliminarCarpeta() {
    }

    public void moverNotas() {
    }
}
