package com.codigo.restTemplate.service;

import com.codigo.restTemplate.aggregates.response.SunatReponse;

public interface ReniecService {
    SunatReponse getInfoReniec(String numRuc);

}
