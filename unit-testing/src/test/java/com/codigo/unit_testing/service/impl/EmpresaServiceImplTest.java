package com.codigo.unit_testing.service.impl;

import com.codigo.unit_testing.aggregates.constants.Constants;
import com.codigo.unit_testing.aggregates.request.EmpresaRequest;
import com.codigo.unit_testing.aggregates.response.BaseResponse;
import com.codigo.unit_testing.dao.EmpresaRepository;
import com.codigo.unit_testing.entity.Empresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EmpresaServiceImplTest {

    @Mock  //Simulamos el repositorio de empresa
    private EmpresaRepository empresaRepository;
    @InjectMocks //Inyectamos el servicio que vamos a probar
    private EmpresaServiceImpl empresaService;
    @Captor
    private ArgumentCaptor<Empresa> empresaCaptor;
    //Variables globlales
    private Empresa empresa; //Instancia de empresa que sera usada en los tests
    private EmpresaRequest empresaRequest; //Instancia de request que sera usado en los test


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializar los mock antes de cada prueba
        empresa = new Empresa(); // Creando una instancia de empresa
        empresaRequest = new EmpresaRequest(); // creando una instancia de empresaRequest
        empresaRequest.setNumeroDocumento("123456789");
    }

    @Test
    void testCrearEmpresaExiste(){
        //ARRANGE
        //Configurar el mock para simular que la empresa ya existe
        when(empresaRepository.existsByNumeroDocumento(anyString())).thenReturn(true);

        //ACT
        //EJECUTAR EL METODO QUE ESTAMOS PROBANDO
        ResponseEntity<BaseResponse<Empresa>> response = empresaService.crear(empresaRequest);

        //ASSERT
        //Verificar los resultado obtenidos
        assertNotNull(response); //La respuesta no debe ser nula
        assertNotNull(response.getBody()); // El cuerpo de la respuesta no debe ser nulo
        assertEquals(Constants.CODE_EXIST, response.getBody().getCode(), "Codigo recibido " +
                "para Empresa Existente es INCORRECTO");

    }

    @Test
    void testCrearEmpresaNueva(){

        //ARRANGE
        //Configurar el mock para simular que la empresa NO existe y luego se guarde correctamente
        when(empresaRepository.existsByNumeroDocumento(anyString())).thenReturn(false);
        when(empresaRepository.save(any())).thenReturn(empresa);

        //ACT
        //EJECUTAR EL METODO QUE ESTAMOS PROBANDO
        ResponseEntity<BaseResponse<Empresa>> response = empresaService.crear(empresaRequest);

        //ASSERT
        //Verificar los resultado obtenidos
        assertNotNull(response); //La respuesta no debe ser nula
        assertNotNull(response.getBody()); // El cuerpo de la respuesta no debe ser nulo
        assertEquals(Constants.CODE_OK, response.getBody().getCode(), "Codigo recibido " +
                "para Empresa NUEVA es INCORRECTO");
        assertEquals(Constants.MSJ_OK, response.getBody().getMessage(), "MENSAJE recibido " +
                "para Empresa NUEVA es INCORRECTO");
        assertTrue(response.getBody().getObjeto().isPresent(), "Objeto no deberia ser vacio");
        assertSame(empresa, response.getBody().getObjeto().get(), "Objeto Retornado no es el mismo");

        verify(empresaRepository, times(1)).save(empresaCaptor.capture());
        assertEquals("123456789", empresaCaptor.getValue().getNumeroDocumento());



    }

    @Test
    void testObtenerEmpresaHappyPath(){
        //ARRANGE
        //Definimos el comportamiento de nuestro mock
        when(empresaRepository.findById(any())).thenReturn(Optional.of(empresa));

        //ACT
        ResponseEntity<BaseResponse<Empresa>> resultado = empresaService.obtenerEmpresa(1L);

        //ASSERT
        assertNotNull(resultado);
        assertNotNull(resultado.getBody());
        assertEquals(2001,resultado.getBody().getCode());
        assertEquals(Constants.MSJ_OK,resultado.getBody().getMessage());
        assertTrue(resultado.getBody().getObjeto().isPresent());
        assertSame(empresa,resultado.getBody().getObjeto().get());
    }

    @Test
    void testObtenerEmpresaNoExisteHappyPath(){
        //ARRANGE
        //Definimos el comportamiento de nuestro mock
        when(empresaRepository.findById(any())).thenReturn(Optional.empty());

        //ACT
        ResponseEntity<BaseResponse<Empresa>> resultado = empresaService.obtenerEmpresa(1L);

        //ASSERT
        assertNotNull(resultado);
        assertNotNull(resultado.getBody());
        assertEquals(Constants.CODE_EMPRESA_NO_EXIST,resultado.getBody().getCode());
        assertEquals(Constants.MSJ_EMPRESA_NO_EXIST,resultado.getBody().getMessage());
        assertFalse(resultado.getBody().getObjeto().isPresent());
        //assertSame(empresa,resultado.getBody().getObjeto().get());
    }

    @Test
    void testActualziarEmpresaHappyPath(){
        //arrange
        Long id = 1L;
        when(empresaRepository.existsById(id)).thenReturn(true);
        when(empresaRepository.findById(id)).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(any())).thenReturn(empresa);

        //act
        ResponseEntity<BaseResponse<Empresa>> resultado = empresaService.actualizar(id,empresaRequest);

        //assert
        assertNotNull(resultado);
        assertNotNull(resultado.getBody());
        assertEquals(Constants.CODE_OK,resultado.getBody().getCode());
        assertEquals(Constants.MSJ_OK,resultado.getBody().getMessage());
        assertTrue(resultado.getBody().getObjeto().isPresent());
        assertSame(empresa, resultado.getBody().getObjeto().get());
    }
    @Test
    void testActualziarEmpresaNoExisteHappyPath(){
        //arrange
        Long id = 1L;
        when(empresaRepository.existsById(id)).thenReturn(false);

        //act
        ResponseEntity<BaseResponse<Empresa>> resultado = empresaService.actualizar(id,empresaRequest);

        //assert
        assertNotNull(resultado);
        assertNotNull(resultado.getBody());
        assertEquals(Constants.CODE_EMPRESA_NO_EXIST,resultado.getBody().getCode());
        assertEquals(Constants.MSJ_EMPRESA_NO_EXIST,resultado.getBody().getMessage());
        assertFalse(resultado.getBody().getObjeto().isPresent());

    }

}