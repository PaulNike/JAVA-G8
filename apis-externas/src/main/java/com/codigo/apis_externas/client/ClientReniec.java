package com.codigo.apis_externas.client;

import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "client-reniec", url = "https://api.apis.net.pe/v2/reniec/")
//https://api.apis.net.pe/ --> Host
// v2/reniec/ --> Acceso al controller o Path
// dni --> EndPoint
// ?numero=06256217 --> Parametros
public interface ClientReniec {

    @GetMapping(value = "/dni", produces = "application/json" )
    ReniecResponse getPersona(@RequestParam("numero") String numero,
                              @RequestHeader("Authorization") String authorization);

}
