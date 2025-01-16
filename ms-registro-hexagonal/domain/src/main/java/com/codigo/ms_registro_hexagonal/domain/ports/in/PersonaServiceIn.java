package com.codigo.ms_registro_hexagonal.domain.ports.in;

import com.codigo.ms_registro_hexagonal.domain.aggregates.dto.PersonaDto;

public interface PersonaServiceIn {
    PersonaDto crearPersonaIn(String dni);
}
