package com.codigo.retrofit.controller;

import com.codigo.retrofit.aggregates.response.ResponseSunat;
import com.codigo.retrofit.service.SunatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/retrofit/")
public class SunatController {
    private final SunatService sunatService;

    @GetMapping("/infosunat")
    public ResponseEntity<ResponseSunat> getInfoSunat(
            @RequestParam("ruc") String ruc) throws IOException {
        return new ResponseEntity<>(sunatService.getInfoSunat(ruc), HttpStatus.OK);
    }

}
