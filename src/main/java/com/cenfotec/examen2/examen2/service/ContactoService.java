package com.cenfotec.examen2.examen2.service;

import com.cenfotec.examen2.examen2.domain.Cliente;
import com.cenfotec.examen2.examen2.domain.Contacto;
import com.cenfotec.examen2.examen2.repository.ClienteRepository;
import com.cenfotec.examen2.examen2.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {
    @Autowired
    ContactoRepository contactoRepository;

    public void guardarContacto(Contacto contacto){
        contactoRepository.save(contacto);
    }

    public List<Contacto> getAll(){
        return contactoRepository.findAll();
    }

    public Optional<Contacto> getById(int id) {
        return contactoRepository.findById(Long.valueOf(id));
    }

    public void updateContacto(Contacto contacto) {
        contactoRepository.save(contacto);
    }
}
