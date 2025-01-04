package com.codigo.persistencia.service;

import com.codigo.persistencia.aggregates.request.RequestEstudiante;
import com.codigo.persistencia.aggregates.response.ResponseBase;
import com.codigo.persistencia.entity.EstudianteEntity;

import java.util.List;

public interface EstudianteService {
    EstudianteEntity guardarEstudiante(RequestEstudiante requestEstudiante);
    EstudianteEntity obtenerEstudiante(String numDoc);
    List<EstudianteEntity> obtenerTodos();
    List<EstudianteEntity> obtenerTodosPorCurso(String curso);
    List<EstudianteEntity> obtenerTodosPorCursoFuncion(String curso);
    List<EstudianteEntity> obtenerTodosPorEstado(int estado);
    EstudianteEntity actualizarEstudiante(String numDoc,
                                          RequestEstudiante requestEstudiante);
    void eliminarEstudiante(String numDoc);
    void eliminarEstudianteLogico(String numDoc);

    ResponseBase<EstudianteEntity> guardarV2(RequestEstudiante requestEstudiante);

}
