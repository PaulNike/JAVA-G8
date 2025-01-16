package com.codigo.ms_registro_hexagonal.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numDoc;
    private String tipoDoc;
    private String nombres;
    private String apellidos;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCrea;
}
