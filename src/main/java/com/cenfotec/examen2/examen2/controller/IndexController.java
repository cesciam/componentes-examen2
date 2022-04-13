package com.cenfotec.examen2.examen2.controller;

import com.cenfotec.examen2.examen2.domain.Auditor;
import com.cenfotec.examen2.examen2.service.AuditorService;
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
public class IndexController {

    @Autowired
    AuditorService auditorService;

    @RequestMapping ("/")
    public String index (Model model){
        return "index";
    }

    @RequestMapping(value = "/agregarAuditor", method = RequestMethod.GET)
    public String navegarPaginaInsertar(Model model){
        model.addAttribute(new Auditor());
        return "/agregarAuditor";
    }

    @RequestMapping(value = "/agregarAuditor", method = RequestMethod.POST)
    public String accionPaginaInsertar(Auditor auditor, BindingResult result, Model model){
        if(auditor.getDisponibilidad().toLowerCase().equals("nd")){
            auditor.setDisponibilidad("No disponible");
        } else {
            auditor.setDisponibilidad("Disponible");
        }

        switch (auditor.getEspecialidad().toLowerCase()){
            case "sl": {
                auditor.setEspecialidad("Seguridad Laboral");
                break;
            }
            case "sti": {
                auditor.setEspecialidad("Seguridad de TI");
                break;
            }
        }
        auditor.setEstado("Activo");
        auditorService.guardarAuditor(auditor);
        return "exito";
    }

    @RequestMapping("/verAuditores")
    public String verAuditores (Model model){
        model.addAttribute("auditor", auditorService.getAll());
        return "verAuditores";
    }

    @RequestMapping(value = "/editarAuditor/{id}")
    public String irAEditar(Model model, @PathVariable int id) {
        Optional<Auditor> auditorToEdit = auditorService.getById(id);
        if (auditorToEdit.isPresent()){
            model.addAttribute("auditorToEdit", auditorToEdit);
            return "editAuditorForm";
        } else {
            return "notFound";
        }
    }

    @RequestMapping(value = "/editarAuditor/{id}", method = RequestMethod.POST)
    public String guardarCambios(Auditor auditor, BindingResult result,Model model,
                                 @PathVariable int id) {
        auditorService.updateAuditor(auditor);
        return "exito";
    }
}
