package com.ortega.springcloud.msvc.cursos.controller;

import com.ortega.springcloud.msvc.cursos.model.entity.Curso;
import com.ortega.springcloud.msvc.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CursoController {
    @Autowired
    CursoService cursoService;
    @GetMapping
    public ResponseEntity listCursos(){
        return ResponseEntity.ok(cursoService.listCursos());
    }
    @GetMapping("/{id}")
    public ResponseEntity getCurso(@PathVariable Long id){
        Optional<Curso> cursoOptional = cursoService.getById(id);
        if(cursoOptional.isPresent()){
            return ResponseEntity.ok(cursoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity saveCurso(@RequestBody Curso curso){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.saveCurso(curso));
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editCurso(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> optional = cursoService.getById(id);
        if(optional.isPresent()){
            Curso cursoDB = optional.get();
            cursoDB.setNombre(curso.getNombre());
            curso.setUsuario(curso.getUsuario());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.saveCurso(cursoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCurso(@PathVariable Long id){
        Optional<Curso> optional = cursoService.getById(id);
        if(optional.isPresent()){
            cursoService.deleteCurso(id);
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
