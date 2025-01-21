package com.codigo.patron_builder.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private Integer anio;
    private String marca;
    private String tipo;
    @OneToOne(cascade = CascadeType.ALL)
    private Transmision transmision;


}
