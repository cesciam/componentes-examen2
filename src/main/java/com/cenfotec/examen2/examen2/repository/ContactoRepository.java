package com.cenfotec.examen2.examen2.repository;

import com.cenfotec.examen2.examen2.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long>{
}
