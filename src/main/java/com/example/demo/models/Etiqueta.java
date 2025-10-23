package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad Etiqueta
 * Permite clasificar las notas mediante nombres y colores.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "etiquetas")
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String color;

    @ManyToMany(mappedBy = "etiquetas")
    private List<Nota> notas;

    public void crearEtiqueta() {
    }

    public void editarEtiqueta() {
    }

    public void eliminarEtiqueta() {
    }
}
