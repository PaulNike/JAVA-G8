package com.codigo.restTemplate.service;

import com.codigo.restTemplate.aggregates.response.SunatReponse;

public interface SunatService {
   SunatReponse getInfoSunat(String numRuc);
}
