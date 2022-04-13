package com.cenfotec.examen2.examen2.service;

import com.cenfotec.examen2.examen2.domain.Author;
import com.cenfotec.examen2.examen2.domain.Journal;
import com.cenfotec.examen2.examen2.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    JournalRepository journalRepository;

    public void saveJournal(Journal journal){
        Author nuevo = new Author();
        nuevo.setName("Cualquiera " + Instant.now().getEpochSecond());
        List<Author> authors = new ArrayList<>();
        authors.add(nuevo);
        journal.setAuthors(authors);
        journalRepository.save(journal);
    }

    public List<Journal> getAll(){
        return journalRepository.findAll();
    }

    public Optional<Journal> getById(int id) {
        return journalRepository.findById(Long.valueOf(id));
    }

    public void updateJournal(Journal journal) {
        journalRepository.save(journal);
    }

    public void deleteJournal(int id) {
        journalRepository.deleteById(Long.valueOf(id));
    }
}
