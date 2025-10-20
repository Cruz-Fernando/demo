package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entidad Recordatorio
 * Permite establecer alertas o notificaciones sobre las notas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recordatorios")
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime fechaHora;
    private String mensaje;
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "nota_id")
    private Nota nota;

    public void crearRecordatorio() {}
    public void editarRecordatorio() {}
    public void desactivarRecordatorio() {}
}
