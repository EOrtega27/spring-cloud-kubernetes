package com.ortega.springcloud.msvc.cursos.controller;

import com.ortega.springcloud.msvc.cursos.model.entity.Curso;
import com.ortega.springcloud.msvc.cursos.model.pojo.Usuario;
import com.ortega.springcloud.msvc.cursos.service.CursoService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.*;

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
    public ResponseEntity saveCurso(@Valid @RequestBody Curso curso, BindingResult result){
        if(result.hasErrors()){
            return  dataValidation(result);
        }
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.saveCurso(curso));
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editCurso(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return  dataValidation(result);
        }
        Optional<Curso> optional = cursoService.getById(id);
        if(optional.isPresent()){
            Curso cursoDB = optional.get();
            cursoDB.setNombre(curso.getNombre());
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

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> addUsuarioToCurso(@PathVariable Long cursoId, @RequestBody Usuario usuario){
        Optional<Usuario> optionalUsuario;
        try{
            optionalUsuario = cursoService.addUsuario(usuario, cursoId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", e.getMessage()));
        }
        if(optionalUsuario.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUsuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> deleteUsuarioFromCurso(@PathVariable Long cursoId, @RequestBody Usuario usuario){
        Optional<Usuario> optionalUsuario;
        try{
            optionalUsuario = cursoService.removeUsuario(usuario, cursoId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", e.getMessage()));
        }
        if(optionalUsuario.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUsuario.get());
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<?> dataValidation(BindingResult result){
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(
                err -> {
                    errores.put(err.getField(), err.getDefaultMessage());
                }
        );
        return  ResponseEntity.badRequest().body(errores);
    }
}
