package com.example.demo.repositories;

import com.example.demo.models.Nota;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {

    List<Nota> findByTituloContainingIgnoreCase(String titulo);

    List<Nota> findByEsPublicaTrue();

    List<Nota> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT n FROM Nota n JOIN n.etiquetas e WHERE LOWER(e.nombre) = LOWER(:nombreEtiqueta)")
    List<Nota> findNotasByEtiqueta(String nombreEtiqueta);

    @Query("SELECT n.colorFondo, COUNT(n) FROM Nota n GROUP BY n.colorFondo")
    List<Object[]> countNotasByColorFondo();
}
