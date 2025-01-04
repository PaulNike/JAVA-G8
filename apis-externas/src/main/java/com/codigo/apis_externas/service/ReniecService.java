package com.codigo.apis_externas.service;

import com.codigo.apis_externas.aggregates.response.ReniecResponse;

public interface ReniecService {

    ReniecResponse buscarPorDni(String numDni);
}
