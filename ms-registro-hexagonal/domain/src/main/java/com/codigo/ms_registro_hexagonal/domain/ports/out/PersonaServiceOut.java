package com.codigo.ms_registro_hexagonal.domain.ports.out;

import com.codigo.ms_registro_hexagonal.domain.aggregates.dto.PersonaDto;

public interface PersonaServiceOut {

    PersonaDto crearPersonaOut(String dni);
}
