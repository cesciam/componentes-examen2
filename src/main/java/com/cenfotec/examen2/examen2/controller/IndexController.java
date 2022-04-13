package com.cenfotec.examen2.examen2.controller;

import com.cenfotec.examen2.examen2.domain.Auditor;
import com.cenfotec.examen2.examen2.domain.Cliente;
import com.cenfotec.examen2.examen2.domain.Contacto;
import com.cenfotec.examen2.examen2.service.AuditorService;
import com.cenfotec.examen2.examen2.service.ClienteService;
import com.cenfotec.examen2.examen2.service.ContactoService;
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

    @Autowired
    ClienteService clienteService;

    @Autowired
    ContactoService contactoService;

    @RequestMapping ("/")
    public String index (Model model){
        return "index";
    }

    // Mantenimiento de auditor
    @RequestMapping(value = "/agregarAuditor", method = RequestMethod.GET)
    public String navegarPaginaInsertarAuditor(Model model){
        model.addAttribute(new Auditor());
        return "/agregarAuditor";
    }

    @RequestMapping(value = "/agregarAuditor", method = RequestMethod.POST)
    public String accionPaginaInsertarAuditor(Auditor auditor, BindingResult result, Model model){
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
    public String irAEditarAuditor(Model model, @PathVariable int id) {
        Optional<Auditor> auditorToEdit = auditorService.getById(id);
        if (auditorToEdit.isPresent()){
            model.addAttribute("auditorToEdit", auditorToEdit);
            return "editAuditorForm";
        } else {
            return "notFound";
        }
    }

    @RequestMapping(value = "/editarAuditor/{id}", method = RequestMethod.POST)
    public String guardarCambiosAuditor(Auditor auditor, BindingResult result,Model model, @PathVariable int id) {
        auditorService.updateAuditor(auditor);
        return "exito";
    }
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Mantenimiento de clientes
    @RequestMapping(value = "/agregarCliente", method = RequestMethod.GET)
    public String navegarPaginaInsertarCliente(Model model){
        model.addAttribute(new Cliente());
        return "/agregarCliente";
    }

    @RequestMapping(value = "/agregarCliente", method = RequestMethod.POST)
    public String accionPaginaInsertarCliente(Cliente cliente, BindingResult result, Model model){
        clienteService.guardarCliente(cliente);
        return "exito";
    }
    @RequestMapping("/verClientes")
    public String verClientes (Model model){
        model.addAttribute("clientes", clienteService.getAll());
        return "verClientes";
    }
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Mantenimiento de contactos
    @RequestMapping(value = "/agregarContacto/{id}")
    public String irFormContacto(Model model, @PathVariable int id) {
        Optional<Cliente> cliente = clienteService.getById(id);
        Contacto contacto = new Contacto();
        contacto.setCliente(cliente.get());
        model.addAttribute(contacto);
        return "/agregarContacto";
    }

    @RequestMapping(value = "/agregarContacto/{id}", method = RequestMethod.POST)
    public String guardarContacto(Contacto contacto, BindingResult result,Model model, @PathVariable int id) {
        Cliente clienteContac = clienteService.getById(id).get();
        clienteContac.getContactos().add(contacto);
        clienteService.updateCliente(clienteContac);
        return "exito";
    }

    @RequestMapping(value = "/editarContacto/{id}")
    public String irAEditarContacto(Model model, @PathVariable int id) {
        Optional<Contacto> contactoToEdit = contactoService.getById(id);
        if (contactoToEdit.isPresent()){
            model.addAttribute("contactoToEdit", contactoToEdit);
            return "editContactoForm";
        } else {
            return "notFound";
        }
    }

    @RequestMapping(value = "/editarContacto/{id}", method = RequestMethod.POST)
    public String guardarCambiosContacto(Contacto contacto, BindingResult result,Model model, @PathVariable int id) {
        Optional<Contacto> contactoBefore = contactoService.getById(id);
        contacto.setCliente(contactoBefore.get().getCliente());
        contactoService.updateContacto(contacto);
        return "exito";
    }

    @RequestMapping(value = "/borrarContacto/{id}")
    public String borrar(Model model, @PathVariable int id) {
        contactoService.deleteContacto(id);
        List<Contacto> contactos = contactoService.getAll();
        return "exito";
    }
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
