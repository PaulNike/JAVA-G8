package com.codigo.apis_externas.controller;

import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import com.codigo.apis_externas.aggregates.response.ResponseBase;
import com.codigo.apis_externas.entity.PersonaEntity;
import com.codigo.apis_externas.service.ReniecService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
//TAG A DEFINIR AQUI APLICA PARA TODOS LOS ENDPOINTS DE ESTE CONTROLADOR
/*@Tags({
        @Tag(
                name = "ConsultaReniec",
                description = "Esta api contiene los endPoints necesarios para la consulta y " +
                        "registro de una persona",
                externalDocs = @ExternalDocumentation(
                        description = "Documentacion adicional",
                        url = "https://github.com/PaulNike/Java-G8"
                )
        )
})*/
public class ReniecController {

    @Autowired
    private ReniecService reniecService;

    @Tag(
            name = "ConsultaReniec",
            description = "Esta api contiene los endPoints necesarios para la consulta y " +
                    "registro de una persona",
            externalDocs = @ExternalDocumentation(
                    description = "Documentacion adicional",
                    url = "https://github.com/PaulNike/Java-G8"
            )
    )
    @GetMapping("/buscarPorDni/{numDni}")
    @Operation(summary = "Buscar una Persona",
            description = "Busqueda de personas por medio del numero de documento de identidad",
            parameters = @Parameter(
                    name = "numDni",
                    description = "Numero Dni",
                    //required = true,
                    example = "12345678"
            ))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operación Exitosa",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReniecResponse.class),
                    examples = {
                            @ExampleObject(
                                    name = "Ejemplo de los datos Reniec",
                                    value = "{\n" +
                                            "    \"nombres\": \"PAUL NIKE\",\n" +
                                            "    \"apellidoPaterno\": \"RODRIGUEZ\",\n" +
                                            "    \"apellidoMaterno\": \"MIJAHUANGA\",\n" +
                                            "    \"tipoDocumento\": \"1\",\n" +
                                            "    \"numeroDocumento\": \"72032008\",\n" +
                                            "    \"digitoVerificador\": \"3\"\n" +
                                            "}"
                            )
                    }),
                    @Content(mediaType = "application/xml",
                            schema = @Schema(implementation = ReniecResponse.class),
                    examples = {
                            @ExampleObject(
                                    name = "Ejemplo de datos XML",
                                    value = "     <nombres>PAUL NIKE</nombres>\n" +
                                            "     <apellidoPaterno>RODRIGUEZ</apellidoPaterno>\n" +
                                            "     <apellidoMaterno>MIJAHUANGA</apellidoMaterno>\n" +
                                            "     <tipoDocumento>1</tipoDocumento>\n" +
                                            "     <numeroDocumento>72032008</numeroDocumento>\n" +
                                            "     <digitoVerificador>3</digitoVerificador>"
                            )
                    })
            }),
            @ApiResponse(responseCode = "400", description = "Error en el request",
            content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<ReniecResponse> buscarPorDNI(
            @PathVariable String numDni){
        return new ResponseEntity<>(reniecService.buscarPorDni(numDni), HttpStatus.OK);
    }


    @PostMapping("/resgitrarPersona/{numDni}")
    public ResponseEntity<ResponseBase<PersonaEntity>> resgitrarPersona(@PathVariable String numDni){
        return new ResponseEntity<>(reniecService.resgitrarPersona(numDni), HttpStatus.OK);
    }


    @GetMapping("/buscarPorDni2/{numDni}")
    public ResponseEntity<ReniecResponse> buscarPorDNI2(@PathVariable String numDni){
        return new ResponseEntity<>(reniecService.buscarPorDni(numDni), HttpStatus.OK);
    }

}
