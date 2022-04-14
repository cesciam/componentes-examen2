package com.cenfotec.examen2.examen2.service;

import com.cenfotec.examen2.examen2.domain.Contacto;
import com.cenfotec.examen2.examen2.domain.Visita;
import com.cenfotec.examen2.examen2.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitaService {

    @Autowired
    VisitaRepository visitaRepository;
    public void guardarVisita(Visita visita){
        visitaRepository.save(visita);
    }

    public List<Visita> getAll(){
        return visitaRepository.findAll();
    }

    public Optional<Visita> getById(int id) {
        return visitaRepository.findById(Long.valueOf(id));
    }
}
