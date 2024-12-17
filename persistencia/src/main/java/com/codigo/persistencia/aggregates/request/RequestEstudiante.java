package com.codigo.persistencia.aggregates.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RequestEstudiante {
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numDocumento;
    private Set<Long> cursos;
}
