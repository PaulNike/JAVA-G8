package com.codigo.unit_testing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraServiceTest {

    private CalculadoraService service;

    @BeforeEach
    void setUp(){
        service = new CalculadoraService();

    }

    //Happypath
    @Test
    void testSumarHappyPath(){
        int resultado = service.sumar(2,3);
        assertEquals(5,resultado, "ERROR EN EL RESUTLADO");
    }

    @Test
    void testRestarHappyPath(){
        int resultado = service.restar(5,3);
        assertEquals(2,resultado);
    }

    @Test
    void testDividirHappyPath(){
        int resultado = service.dividir(10,2);
        assertEquals(5,resultado);
    }

    @Test
    void testEsParHappyPathTrue(){
        assertTrue(service.esPar(4));
    }
    @Test
    void testEsParHappyPathFalse(){
        assertFalse(service.esPar(5));
    }

    @Test
    void testEstaEnRangoHappyPath(){
        assertTrue(service.estaEnRango(5,1,10));
    }

    @Test
    void testSumarNumerosGrandesHappyPath(){
        int resultado = service.sumar(1_000_000, 2_000_000);
        assertEquals(3_000_000, resultado);
    }

    @Test
    void restarNumeroNegativosHappyPath(){
        int resultado = service.restar(5,8);
        assertEquals(-3, resultado);
    }

    //Error testing
    @Test
    void testDividirPorCero(){
        assertThrows(ArithmeticException.class, () ->
                service.dividir(10,0));
    }
}