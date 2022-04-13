package com.cenfotec.examen2.examen2.repository;

import com.cenfotec.examen2.examen2.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
    Journal findJournalByTitle(String title);
}
