package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entidad Multimedia
 * Representa archivos asociados a las notas (imágenes, audios, videos).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreArchivo;
    private String tipoArchivo;
    private String urlArchivo;
    private Float tamaño;
    private LocalDateTime fechaSubida = LocalDateTime.now();

    // Relación con Nota
    @ManyToOne
    @JoinColumn(name = "nota_id")
    private Nota nota;

    public void subirArchivo() {}
    public void eliminarArchivo() {}
    public void descargarArchivo() {}
}
