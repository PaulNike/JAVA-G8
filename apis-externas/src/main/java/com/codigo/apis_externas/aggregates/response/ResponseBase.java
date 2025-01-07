package com.codigo.apis_externas.aggregates.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
@Getter
@Setter
public class ResponseBase<T> {
    private Integer codigo;
    private String mensaje;
    private Optional<T> entidad;

}
