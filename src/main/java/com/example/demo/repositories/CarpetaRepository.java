package com.example.demo.repositories;

import com.example.demo.models.Carpeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarpetaRepository extends JpaRepository<Carpeta, Integer> { }
