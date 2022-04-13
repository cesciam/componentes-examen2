package com.cenfotec.examen2.examen2.service;

import com.cenfotec.examen2.examen2.domain.Auditor;
import com.cenfotec.examen2.examen2.repository.AuditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditorService {
    @Autowired
    AuditorRepository auditorRepository;

    public void guardarAuditor(Auditor auditor){
        auditorRepository.save(auditor);
    }
}
