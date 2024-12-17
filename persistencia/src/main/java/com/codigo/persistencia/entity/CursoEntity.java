package com.codigo.persistencia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "curso")
@Getter
@Setter
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descripcion", length = 100, unique = true)
    private String descripcion;
    @Column(name = "estado", length = 100, nullable = false)
    private Integer estado;
    @ManyToMany(mappedBy = "cursos")
    @JsonBackReference
    //@JsonIgnore
    private Set<EstudianteEntity> estudiantes;

}
