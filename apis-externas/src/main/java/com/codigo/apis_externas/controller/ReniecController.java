package com.codigo.apis_externas.controller;

import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import com.codigo.apis_externas.service.ReniecService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ReniecController {

    @Autowired
    private ReniecService reniecService;

    @GetMapping("/buscarPorDni/{numDni}")
    public ResponseEntity<ReniecResponse> buscarPorDNI(@PathVariable String numDni){
        return new ResponseEntity<>(reniecService.buscarPorDni(numDni), HttpStatus.OK);
    }
}
