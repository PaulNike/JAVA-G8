package com.codigo.ms_complementario.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ms-seguridad")
public interface SeguridadClient {

    @GetMapping("/apis/codigo/api/admin/v1/")
    String getInfoSaludo(@RequestHeader("Authorization") String authorization);
}
