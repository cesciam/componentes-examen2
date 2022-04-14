package com.cenfotec.examen2.examen2.repository;

import com.cenfotec.examen2.examen2.domain.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {
}
