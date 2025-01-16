package com.codigo.ms_registro_hexagonal.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PersonaDto {
    private Long id;
    private String numDoc;
    private String tipoDoc;
    private String nombres;
    private String apellidos;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCrea;
}
