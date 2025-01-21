package com.codigo.patron_builder.controller;

import com.codigo.patron_builder.model.Carro;
import com.codigo.patron_builder.model.Libro;
import com.codigo.patron_builder.model.Transmision;
import com.codigo.patron_builder.repository.CarroRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/builder")
@RequiredArgsConstructor
public class LibroController {

    private final CarroRepository carroRepository;
    private final EntityManager entityManager;


    @GetMapping("/libroFull")
    public Libro obtenerLibroPersonalizado(){
        return new Libro.Builder()
                .titulo("Paco Yunque")
                .autor("Cesar Vallejo")
                .fechaPublicacion("12/02/1995")
                .estado("ACTIVO")
                .build();
    }

    @GetMapping("/libroNoFull")
    public Libro obtenerLibroNoFullPersonalizado(){
        return new Libro.Builder()
                .titulo("Paco Yunque")
                .estado("ACTIVO")
                .build();
    }

    @GetMapping("/carro1")
    public Carro carro1(){
        return Carro.builder()
                .marca("MAZDA")
                .tipo("SUV")
                .anio(2021)
                .modelo("CX3")
                .transmision(Transmision.builder()
                        .caja("AUTOMATICA")
                        .numeroDeCambios(8)
                        .build())
                .build();

        /*Carro carrito = new Carro();
        //set
        Transmision transmision = new Transmision();
        //set*/
    }
    @PostMapping("/carro2")
    public Carro carro2(@RequestParam String marca,
                        @RequestParam String tipo,
                        @RequestParam Integer anio,
                        @RequestParam String modelo,
                        @RequestParam String caja,
                        @RequestParam Integer cambios){
        Carro carrito = Carro.builder()
                .marca(marca)
                .tipo(tipo)
                .anio(anio)
                .modelo(modelo)
                .transmision(Transmision.builder()
                        .caja(caja)
                        .numeroDeCambios(cambios)
                        .build())
                .build();



        Carro dart = Carro.builder().build();

        return carroRepository.save(carrito);
    }

    @PostMapping("/carro3")
    public String carro3(@RequestParam Long id){
        desasociarCarro(id);
        return "Carro desasociado";
    }

    private  void  desasociarCarro(Long id){
        Carro carro = carroRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Error carro no encontrado")
        );
        //Desasociamos
        entityManager.detach(carro);
        //Carro y transmisión desasociados dek contexto de persistencia
        System.out.println("Carro y su transmisión has sido desasociados.");
    }
    private  void  mergeCarro(Long id){
        Carro carro = carroRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Error carro no encontrado")
        );
        //Desasociamos
        entityManager.merge(carro);
        //Carro y transmisión desasociados dek contexto de persistencia
        System.out.println("Carro y su transmisión has sido desasociados.");
    }
}
