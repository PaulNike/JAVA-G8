package com.codigo.patron_builder.controller;

import com.codigo.patron_builder.model.Persona;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/persona")
@RequiredArgsConstructor
public class PersonaController {
    private final EntityManager entityManager;

    @PostMapping("/detach")
    public String desasociarEnd(@RequestBody Persona persona){
        desasociar(persona);
        return "Persona Desasociada";
    }

    private void desasociar(Persona persona){
        entityManager.detach(persona);
        System.out.println("PERSONA DESASOCIADA DEL CONTEXTO DE PERSISTENCIA.");
        System.out.println("¿Persona Gestionada? " + entityManager.contains(persona));
        System.out.println("¿Pasaporte gestionado? " + entityManager.contains(persona.getPasaporte()));

    }
}
