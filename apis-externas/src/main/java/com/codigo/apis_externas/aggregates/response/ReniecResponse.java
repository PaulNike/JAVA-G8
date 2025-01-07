package com.codigo.apis_externas.aggregates.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReniecResponse {
    //ESTA CLASE NETAMENTE VA RECIBIR LA RESPUESTA DEL API EXTERNA

    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificador;


}
