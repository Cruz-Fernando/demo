package com.example.demo.repositories;

import com.example.demo.models.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {

    // Buscar etiquetas por nombre (ignorando mayúsculas/minúsculas)
    List<Etiqueta> findByNombreContainingIgnoreCase(String nombre);

    // Buscar etiquetas por color exacto
    List<Etiqueta> findByColor(String color);
}
