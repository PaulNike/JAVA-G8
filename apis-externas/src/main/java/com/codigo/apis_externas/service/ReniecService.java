package com.codigo.apis_externas.service;

import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import com.codigo.apis_externas.aggregates.response.ResponseBase;
import com.codigo.apis_externas.entity.PersonaEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ReniecService {

    ReniecResponse buscarPorDni(String numDni);
    ResponseBase<PersonaEntity> resgitrarPersona(String numDni);
}
