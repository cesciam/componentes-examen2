package com.cenfotec.examen2.examen2.controller;

import com.cenfotec.examen2.examen2.domain.Auditor;
import com.cenfotec.examen2.examen2.service.AuditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        if(auditor.getDisponibilidad().toLowerCase().equals("d")){
            auditor.setDisponibilidad("No disponible");
        } else {
            auditor.setDisponibilidad("Disponible");
        }
        auditorService.guardarAuditor(auditor);
        return "exito";
    }
}
