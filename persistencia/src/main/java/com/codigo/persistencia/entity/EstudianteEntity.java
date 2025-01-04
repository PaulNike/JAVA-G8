package com.codigo.persistencia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
//SOLO TE SIRVE SI TU SP, NO DEVUELVE UN CURSOR
@NamedStoredProcedureQuery(
        name = "buscarestudiantesporcurso",
        procedureName = "buscar_estudiantes_por_curso",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombreCurso", type = String.class)
        },
        resultClasses = EstudianteEntity.class
)
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;
    @Column(name = "apellido_estudiante", length = 100, nullable = false)
    private String apellidos;
    @Column(name = "tipoDocumento", length = 15, nullable = false)
    private String tipoDocumento;
    @Column(name = "numDocumento", length = 15, nullable = false, unique = true)
    private String numDocumento;
    @Column(name = "estado", length = 1)
    private Integer estado;

    @ManyToMany
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    @JsonManagedReference
    private Set<CursoEntity> cursos;
}
