package com.codigo.restTemplate.service.impl;

import com.codigo.restTemplate.aggregates.constants.Constants;
import com.codigo.restTemplate.aggregates.response.SunatReponse;
import com.codigo.restTemplate.service.ReniecService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Log4j2
public class ReniecServiceImpl implements ReniecService {

    private final RestTemplate restTemplate;
    @Override
    public SunatReponse getInfoReniec(String numRuc) {
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
        headers.set("Authorization", Constants.BEARER+"token");
        return headers;
    }
}
