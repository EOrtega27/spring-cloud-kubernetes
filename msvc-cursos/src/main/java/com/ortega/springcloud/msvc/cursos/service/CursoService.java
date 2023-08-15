package com.ortega.springcloud.msvc.cursos.service;

import com.ortega.springcloud.msvc.cursos.model.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listCursos();
    Optional<Curso> getById(Long id);
    Curso saveCurso(Curso curso);
    void deleteCurso(Long id);
}
