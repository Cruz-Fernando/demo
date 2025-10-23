package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entidad Perfil
 * Contiene información personal del usuario.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perfiles")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreCompleto;
    private String biografia;
    private String fotoPerfil;
    private String telefono;
    private LocalDate fechaNacimiento;

    // Relación uno a uno con Usuario
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void editarPerfil() {
    }

    public void verPerfil() {
    }
}
