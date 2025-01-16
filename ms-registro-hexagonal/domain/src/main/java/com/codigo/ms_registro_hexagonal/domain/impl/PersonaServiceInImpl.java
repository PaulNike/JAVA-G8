package com.codigo.ms_registro_hexagonal.domain.impl;

import com.codigo.ms_registro_hexagonal.domain.aggregates.constants.Constants;
import com.codigo.ms_registro_hexagonal.domain.aggregates.dto.PersonaDto;
import com.codigo.ms_registro_hexagonal.domain.ports.in.PersonaServiceIn;
import com.codigo.ms_registro_hexagonal.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class PersonaServiceInImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;

    private String serviceName = "PersonaServiceInImpl";
    @Override
    public PersonaDto crearPersonaIn(String dni) {
        String nameMethod = "crearPersonaIn";
        log.info(Constants.LOG_INICIO+ serviceName, nameMethod);

        if (Objects.nonNull(dni)){
            log.info(Constants.LOG_FIN+ serviceName, nameMethod);
           return personaServiceOut.crearPersonaOut(dni);
        }else {
            log.info(Constants.LOG_ERROR+ serviceName, nameMethod);
            throw new RuntimeException("Error en crearPersonaIn, el DNI es nulo");
        }

    }
}
