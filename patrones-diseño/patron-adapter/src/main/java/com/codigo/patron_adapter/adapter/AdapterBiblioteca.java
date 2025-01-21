package com.codigo.patron_adapter.adapter;

import com.codigo.patron_adapter.antiguo.BibliotecaAntigua;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Component
public class AdapterBiblioteca {
    private final BibliotecaAntigua bibliotecaAntigua =
            new BibliotecaAntigua();

    public String btenerDetalle(int idLibro){
        //Obtener la información del XML
        String detalle = bibliotecaAntigua.obtenerInformacion(idLibro);
        //traduccion XML A jSON
        JSONObject jsonObject = XML.toJSONObject(detalle);
        //retornamos respuesta
        return jsonObject.toString();
    }

}
