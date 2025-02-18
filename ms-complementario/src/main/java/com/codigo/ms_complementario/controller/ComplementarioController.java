package com.codigo.ms_complementario.controller;

import com.codigo.ms_complementario.client.SeguridadClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/complementario/v1")
@RequiredArgsConstructor
@RefreshScope
public class ComplementarioController {

    private final SeguridadClient seguridadClient;

    @Value("${datoProp}")
    private String datoProp;
    @GetMapping("/info-users")
    public ResponseEntity<String> getSaludo(
            @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(seguridadClient.getInfoSaludo(token));
    }

    @GetMapping("/pruebacomp")
    public ResponseEntity<String> getPorpiedad(){
        return ResponseEntity.ok(datoProp);

    }
}
