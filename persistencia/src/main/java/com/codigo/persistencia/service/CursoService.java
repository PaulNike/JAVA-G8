package com.codigo.persistencia.service;

import com.codigo.persistencia.aggregates.request.RequestCurso;
import com.codigo.persistencia.entity.CursoEntity;

import java.util.List;

public interface CursoService {
    CursoEntity guardarCurso(RequestCurso curso);
    CursoEntity obtenerCurso(Long id);
    List<CursoEntity> obtenerTodos();
    CursoEntity actualizarCurso(Long id, RequestCurso curso);
    void eliminarCurso(Long id);
}
