package com.codigo.apis_externas.service.impl;

import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import com.codigo.apis_externas.client.ClientReniec;
import com.codigo.apis_externas.service.ReniecService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReniecServiceImpl implements ReniecService {

    @Autowired
    private  ClientReniec clientReniec;

    @Value("${token.api}")
    private String token;
    @Override
    public ReniecResponse buscarPorDni(String numDni) {
        ReniecResponse dato = execution(numDni);
        return execution(numDni);
    }

    //EJECUTANDO EL API EXTERNA
    private ReniecResponse execution(String numDni){
        String tokenOk = "Bearer "+token;
        return clientReniec.getPersona(numDni,tokenOk);
    }
}
