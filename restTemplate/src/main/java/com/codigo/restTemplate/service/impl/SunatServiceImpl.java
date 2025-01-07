package com.codigo.restTemplate.service.impl;

import com.codigo.restTemplate.aggregates.constants.Constants;
import com.codigo.restTemplate.aggregates.response.SunatReponse;
import com.codigo.restTemplate.service.SunatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Log4j2
public class SunatServiceImpl implements SunatService {

    private final RestTemplate restTemplate;


    @Value("${token.api}")
    private String token;

    @Override
    public SunatReponse getInfoSunat(String numRuc) {
        log.info("Consultando información de SUNAR para RUC: {}", numRuc);
        //Consultamos directamente al cliente externo
        SunatReponse sunatReponse = executeRestTemplate(numRuc);
        if(sunatReponse == null){
            log.warn("No se pudo obtener informacion para el RUC: {}", numRuc);
        }else {
            log.info("Información obtenida exitosamente para el RUC: {}", numRuc);
        }
        return sunatReponse;
    }

    private SunatReponse executeRestTemplate(String numRuc){
        //urlComplete = https://api.apis.net.pe/v2/sunat/ruc/full?numero=12345678912
        String urlComplete = Constants.BASE_URL + "/v2/sunat/ruc/full?numero=" + numRuc;

        try {
            ResponseEntity<SunatReponse> response = restTemplate.exchange(
                    urlComplete,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders()),
                    SunatReponse.class
            );

            //validarmos la repsuesta del servicio
            if(response.getStatusCode() == HttpStatus.OK){
                return response.getBody();
            } else {
                log.warn("Respuesta inesperada del servicio externo: {}", response.getStatusCode());
            }
        } catch (Exception e){
            log.error("Error al consultar el servicio externo para el RUC: {}", numRuc, e);
        }

        return  null;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", Constants.BEARER+token);
        return headers;
    }

}
