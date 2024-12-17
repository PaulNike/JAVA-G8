package com.codigo.persistencia.service.impl;

import com.codigo.persistencia.aggregates.contants.Constants;
import com.codigo.persistencia.aggregates.request.RequestCurso;
import com.codigo.persistencia.entity.CursoEntity;
import com.codigo.persistencia.repository.CursoRepository;
import com.codigo.persistencia.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    @Override
    public CursoEntity guardarCurso(RequestCurso curso) {
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setDescripcion(curso.getDescripcion());
        cursoEntity.setEstado(Constants.ESTADO_ACTIVO);
        return cursoRepository.save(cursoEntity);
    }

    @Override
    public CursoEntity obtenerCurso(Long id) {
        return cursoRepository.findById(id).get();
    }

    @Override
    public List<CursoEntity> obtenerTodos() {
        return null;
    }

    @Override
    public CursoEntity actualizarCurso(Long id, RequestCurso curso) {
        return null;
    }

    @Override
    public void eliminarCurso(Long id) {

    }
}
