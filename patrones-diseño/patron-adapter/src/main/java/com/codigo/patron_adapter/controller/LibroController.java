package com.codigo.patron_adapter.controller;

import com.codigo.patron_adapter.adapter.AdapterBiblioteca;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/adapter")
public class LibroController {

    private final AdapterBiblioteca adapterBiblioteca;

    public LibroController(AdapterBiblioteca adapterBiblioteca) {
        this.adapterBiblioteca = adapterBiblioteca;
    }

    @GetMapping("/libro/{id}")
    public String obtenerLibro(@PathVariable Integer id){
        return adapterBiblioteca.btenerDetalle(id);
    }
}
