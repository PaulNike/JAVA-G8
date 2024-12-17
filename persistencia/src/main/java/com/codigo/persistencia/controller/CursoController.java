package com.codigo.persistencia.controller;

import com.codigo.persistencia.aggregates.request.RequestCurso;
import com.codigo.persistencia.entity.CursoEntity;
import com.codigo.persistencia.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping("/save")
    public ResponseEntity<CursoEntity> guardar(@RequestBody RequestCurso curso){
        return new ResponseEntity<>(cursoService.guardarCurso(curso), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CursoEntity> obtenerCurso(@PathVariable("id") Long id){
        return new ResponseEntity<>(cursoService.obtenerCurso(id), HttpStatus.OK);
    }
}
