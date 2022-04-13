package com.cenfotec.examen2.examen2.controller;

import com.cenfotec.examen2.examen2.domain.Journal;
import com.cenfotec.examen2.examen2.service.JournalService;
import com.cenfotec.examen2.examen2.helpers.JournalValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class JournalController {

    @Autowired
    JournalService journalService;

    @Autowired
    JournalValidations journalHelper; //buena idea.

    @RequestMapping("/a")
    public String index(Model model){
        List<Journal> results = journalService.getAll();
        model.addAttribute("journal", journalService.getAll());
        return "index";
    }

    @RequestMapping(value = "/agregarEntrada", method = RequestMethod.GET)
    public String navegarPaginaInsertar(Model model){
        model.addAttribute(new Journal());
        return "agregarEntrada";
    }

    @RequestMapping(value = "/agregarEntrada", method = RequestMethod.POST)
    public String accionPaginaInsertar(Journal journal, BindingResult result, Model model){
        journal.setCreated(Date.from(Instant.now()));
        journalService.saveJournal(journal);
        return "exito";
    }

    @RequestMapping(value = "/editar/{id}")
    public String irAEditar(Model model, @PathVariable int id) {
        Optional<Journal> journalToEdit = journalService.getById(id);
        if (journalToEdit.isPresent()){
            model.addAttribute("journalToEdit", journalToEdit);
            return "editForm";
        } else {
            return "notFound";
        }
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
    public String guardarCambios(Journal journal, BindingResult result,Model model,
                                 @PathVariable int id) {
        journal.setCreated(Date.from(Instant.now())); // esto normalmente
        //podria ir en el service.
        journalService.updateJournal(journal);
        return "exito";
    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(Model model, @PathVariable int id) {
        journalService.deleteJournal(id);
        return "exito";
    }


}
