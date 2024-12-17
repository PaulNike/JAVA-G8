package com.codigo.persistencia.aggregates.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ResponseBase<T> {
    private int codigo;
    private String mensaje;
    private Optional<T> objeto;
}
