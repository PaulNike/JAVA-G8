package com.codigo.apis_externas.controller;

import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import com.codigo.apis_externas.aggregates.response.ResponseBase;
import com.codigo.apis_externas.entity.PersonaEntity;
import com.codigo.apis_externas.service.ReniecService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ReniecController {

    @Autowired
    private ReniecService reniecService;

    @GetMapping("/buscarPorDni/{numDni}")
    public ResponseEntity<ReniecResponse> buscarPorDNI(@PathVariable String numDni){
        return new ResponseEntity<>(reniecService.buscarPorDni(numDni), HttpStatus.OK);
    }

    @PostMapping("/resgitrarPersona/{numDni}")
    public ResponseEntity<ResponseBase<PersonaEntity>> resgitrarPersona(@PathVariable String numDni){
        return new ResponseEntity<>(reniecService.resgitrarPersona(numDni), HttpStatus.OK);
    }

}
