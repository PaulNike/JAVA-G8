package com.codigo.patron_builder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomnbre;

    @OneToOne(cascade = CascadeType.DETACH)
    private Pasaporte pasaporte;
}
