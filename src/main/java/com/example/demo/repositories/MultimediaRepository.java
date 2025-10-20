package com.example.demo.repositories;

import com.example.demo.models.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {
    List<Multimedia> findByNotaId(Integer notaId);
}
