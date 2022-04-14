package com.cenfotec.examen2.examen2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Auditor auditor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

    public Visita(Long id, String nombre, Date fecha, Cliente cliente, Auditor auditor) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.cliente = cliente;
        this.auditor = auditor;
    }

    public Visita() {
    }

    @Override
    public String toString() {
        return "Visita{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", cliente=" + cliente +
                ", auditor=" + auditor +
                '}';
    }
}
