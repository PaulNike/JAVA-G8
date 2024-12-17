package com.codigo.persistencia.controller;

import com.codigo.persistencia.aggregates.contants.Constants;
import com.codigo.persistencia.aggregates.request.RequestEstudiante;
import com.codigo.persistencia.aggregates.response.ResponseBase;
import com.codigo.persistencia.entity.EstudianteEntity;
import com.codigo.persistencia.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping("/save")
    public ResponseEntity<EstudianteEntity> guardar(@RequestBody RequestEstudiante requestEstudiante){
        return new ResponseEntity<>(estudianteService.guardarEstudiante(requestEstudiante),
                HttpStatus.CREATED);
    }
    @GetMapping("/{numdoc}")
    public ResponseEntity<EstudianteEntity> obtenerEstudiante(@PathVariable("numdoc") String numDoc){
        return new ResponseEntity<>(estudianteService.obtenerEstudiante(numDoc), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<EstudianteEntity> obtenerEstudiante2(@RequestParam String numDoc){
        return new ResponseEntity<>(estudianteService.obtenerEstudiante(numDoc), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<EstudianteEntity>> todos(){
        return new ResponseEntity<>(estudianteService.obtenerTodos(), HttpStatus.OK);
    }
    @GetMapping("/todosXcurso")
    public ResponseEntity<List<EstudianteEntity>> todosxCurso(@RequestParam String curso){
        return new ResponseEntity<>(estudianteService.obtenerTodosPorCurso(curso), HttpStatus.OK);
    }


    @PutMapping("/{numDoc}/actualizar")
    public ResponseEntity<EstudianteEntity> actualizar(@PathVariable("numDoc") String numDoc,
                                                       @RequestBody RequestEstudiante requestEstudiante){
        return new ResponseEntity<>(estudianteService.actualizarEstudiante(numDoc,requestEstudiante)
                ,HttpStatus.OK);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminar(@PathVariable("dni") String dni){
        estudianteService.eliminarEstudiante(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/eliminarLogico/{dni}")
    public ResponseEntity<Void> eliminarv2(@PathVariable("dni") String dni){
        estudianteService.eliminarEstudianteLogico(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/savev2")
    public ResponseEntity<ResponseBase<EstudianteEntity>> guardarV2(@RequestBody RequestEstudiante requestEstudiante){
        ResponseBase<EstudianteEntity> respuesta = estudianteService.guardarV2(requestEstudiante);
        if(respuesta.getCodigo() == Constants.CODIGO_OK){
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(respuesta,HttpStatus.CONFLICT);

        }

    }
}
