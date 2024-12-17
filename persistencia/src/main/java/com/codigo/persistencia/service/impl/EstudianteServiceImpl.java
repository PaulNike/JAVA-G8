package com.codigo.persistencia.service.impl;

import com.codigo.persistencia.aggregates.contants.Constants;
import com.codigo.persistencia.aggregates.request.RequestEstudiante;
import com.codigo.persistencia.aggregates.response.ResponseBase;
import com.codigo.persistencia.entity.CursoEntity;
import com.codigo.persistencia.entity.EstudianteEntity;
import com.codigo.persistencia.repository.CursoRepository;
import com.codigo.persistencia.repository.EstudianteRepository;
import com.codigo.persistencia.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    @Override
    public EstudianteEntity guardarEstudiante(RequestEstudiante requestEstudiante) {
        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.setNombres(requestEstudiante.getNombres());
        estudianteEntity.setApellidos(requestEstudiante.getApellidos());
        estudianteEntity.setTipoDocumento(requestEstudiante.getTipoDocumento());
        estudianteEntity.setNumDocumento(requestEstudiante.getNumDocumento());
        estudianteEntity.setEstado(Constants.ESTADO_ACTIVO);
        //Cursos
        Set<CursoEntity> cursos = cargarCursosPorIds(requestEstudiante.getCursos());
        estudianteEntity.setCursos(cursos);
        return estudianteRepository.save(estudianteEntity);
    }

    private Set<CursoEntity> cargarCursosPorIds(Set<Long> cursos) {
        return cursos.stream()
                .map(this::buscarCursosPorId)
                .collect(Collectors.toSet());
    }

    private CursoEntity buscarCursosPorId(Long cursoId) {
        return cursoRepository.findById(cursoId).orElseThrow(
                ()-> new RuntimeException("Error Estudiante --> Curso no encontrado"));
    }

    @Override
    public EstudianteEntity obtenerEstudiante(String numDoc) {
        return estudianteRepository.findByNumDocumento(numDoc).orElseThrow(
                () -> new RuntimeException("Error Estudiante -> Estudiante no encontrado"));
    }

    @Override
    public List<EstudianteEntity> obtenerTodos() {
        return estudianteRepository.findAllByEstado(Constants.ESTADO_ACTIVO);
    }

    @Override
    public List<EstudianteEntity> obtenerTodosPorCurso(String curso) {
        return estudianteRepository.buscarEstudianteXCurso(curso);
    }

    @Override
    public EstudianteEntity actualizarEstudiante(String numDoc, RequestEstudiante requestEstudiante) {
        //Recupero al registro de BD
        EstudianteEntity estudianteExistente = obtenerEstudiante(numDoc);
        //Realizar los cambios a nivel de objeto
        estudianteExistente.setNombres(requestEstudiante.getNombres());
        estudianteExistente.setApellidos(requestEstudiante.getApellidos());
        estudianteExistente.setTipoDocumento(requestEstudiante.getTipoDocumento());
        estudianteExistente.setNumDocumento(requestEstudiante.getNumDocumento());

        //Gestionar los Cursos
        Set<CursoEntity> cursosActuales;
        cursosActuales = cargarCursosPorIds(requestEstudiante.getCursos());
        //asignar cursos nuevos al estudiante
        estudianteExistente.setCursos(cursosActuales);
        return estudianteRepository.save(estudianteExistente);
    }

    //ELIMINADO FISICO
    @Override
    public void eliminarEstudiante(String numDoc) {
        //Identificar el registro
        EstudianteEntity estudianteExistente = obtenerEstudiante(numDoc);
        //eliminando
        estudianteRepository.delete(estudianteExistente);
    }
    //ELIMINADO LOGICO

    @Override
    public void eliminarEstudianteLogico(String numDoc) {
        //Identificar el registro
        EstudianteEntity estudianteExistente = obtenerEstudiante(numDoc);
        estudianteExistente.setEstado(Constants.ESTADO_INACTIVO);
        //eliminando
        estudianteRepository.save(estudianteExistente);
    }



    @Override
    public ResponseBase<EstudianteEntity> guardarV2(RequestEstudiante requestEstudiante) {
        ResponseBase<EstudianteEntity> response = new ResponseBase<>();
        if(estudianteRepository.existsByNumDocumento(requestEstudiante.getNumDocumento())){
            //SIE EXISTE EL DNI EN BASE DE DATOS
            response.setCodigo(Constants.CODIGO_ESTUDIANTE_EXISTE);
            response.setMensaje(Constants.MENSAJE_ESTUDIANTE_EXISTE);
            response.setObjeto(Optional.empty());
            return response;
        }else {
            //NO EXISTE EL DNI EN BASE DE DATOS
            EstudianteEntity estudianteEntity = new EstudianteEntity();
            estudianteEntity.setNombres(requestEstudiante.getNombres());
            estudianteEntity.setApellidos(requestEstudiante.getApellidos());
            estudianteEntity.setTipoDocumento(requestEstudiante.getTipoDocumento());
            estudianteEntity.setNumDocumento(requestEstudiante.getNumDocumento());
            estudianteEntity.setEstado(Constants.ESTADO_ACTIVO);
            //Cursos
            Set<CursoEntity> cursos = cargarCursosPorIds(requestEstudiante.getCursos());
            estudianteEntity.setCursos(cursos);

            //armar respuesta final
            response.setCodigo(Constants.CODIGO_OK);
            response.setMensaje(Constants.MENSAJE_OK);
            response.setObjeto(Optional.of(estudianteRepository.save(estudianteEntity)));
            return response;
        }
    }
}
