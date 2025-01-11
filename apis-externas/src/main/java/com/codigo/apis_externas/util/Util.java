package com.codigo.apis_externas.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Util {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String convertirAString(T objeto) {
        try {
            return OBJECT_MAPPER.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
        log.error("OCURRIO UN ERROR EN convertirAString ->  {}", e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertirDesdeString(String datoRedis,
                                             Class<T> tipoClase)
            {
                try {
                    return OBJECT_MAPPER.readValue(datoRedis,tipoClase);
                } catch (JsonProcessingException e) {
                    log.error("OCURRIO UN ERROR EN convertirDesdeString ->  {}", e);
                    throw new RuntimeException(e);
                }
            }
}
