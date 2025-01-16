package com.codigo.ms_registro_hexagonal.application.controller;

import com.codigo.ms_registro_hexagonal.domain.aggregates.dto.PersonaDto;
import com.codigo.ms_registro_hexagonal.domain.ports.in.PersonaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hexagonal")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaServiceIn personaServiceIn;

    @PostMapping("/{dni}")
    public ResponseEntity<PersonaDto> crearPersona(
            @PathVariable("dni") String dni){
        return ResponseEntity.ok(personaServiceIn.crearPersonaIn(dni));
    }

}
