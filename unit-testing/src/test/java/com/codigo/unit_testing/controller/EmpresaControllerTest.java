package com.codigo.unit_testing.controller;

import com.codigo.unit_testing.aggregates.request.EmpresaRequest;
import com.codigo.unit_testing.aggregates.response.BaseResponse;
import com.codigo.unit_testing.entity.Empresa;
import com.codigo.unit_testing.service.EmpresaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmpresaController.class)
class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpresaService empresaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Empresa empresa;
    private EmpresaRequest empresaRequest;
    private BaseResponse<Empresa> baseResponse;

    @BeforeEach
    void setUp() {
        empresa = new Empresa();
        empresaRequest = new EmpresaRequest();
        baseResponse = new BaseResponse<>();
    }

    @Test
    void testRegistrarHappyPath() throws Exception {
        //Request
        empresaRequest.setNumeroDocumento("123456789");
        //Empreas de respuesta del servicio
        empresa.setId(1L);
        empresa.setNumeroDocumento("123456789");

        baseResponse.setObjeto(Optional.of(empresa));

        when(empresaService.crear(any(EmpresaRequest.class)))
                .thenReturn(ResponseEntity.ok(baseResponse));

        //Ejecutar la petición la simulación la hacemos con
        // MockMvcRequestBuilders se debe importar manualmente
        mockMvc.perform(post("/empresa/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empresaRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.objeto.id").value(1))
                .andExpect(jsonPath("$.objeto.numeroDocumento").value("123456789"));

    }


}