package com.codigo.restTemplate.controller;

import com.codigo.restTemplate.aggregates.response.SunatReponse;
import com.codigo.restTemplate.service.Pagos;
import com.codigo.restTemplate.service.SunatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/consulta/")
@RequiredArgsConstructor
public class SunatController {
    private final SunatService sunatService;
    private final RestTemplate restTemplate;

    @Qualifier("pagosImplTC")
    private final Pagos pagos;
    @Qualifier("pagosImplTD")
    private final Pagos pagos2;

    @GetMapping("/infosunat")
    public ResponseEntity<SunatReponse> getInfo(@RequestParam("ruc") String ruc){
        return new ResponseEntity<>(sunatService.getInfoSunat(ruc), HttpStatus.OK);
    }
}
