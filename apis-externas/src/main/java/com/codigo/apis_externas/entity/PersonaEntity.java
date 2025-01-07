package com.codigo.apis_externas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "personas")
@Getter
@Setter
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    @Column(name = "numeroDocumento", unique = true)
    private String numeroDocumento;
    private String digitoVerificador;
    private String estado;
    private String user_create;
    private Timestamp date_create;
    private String user_delete;
    private Timestamp date_delete;
}
