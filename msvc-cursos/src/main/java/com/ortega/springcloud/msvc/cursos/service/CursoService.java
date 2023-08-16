package com.ortega.springcloud.msvc.cursos.service;

import com.ortega.springcloud.msvc.cursos.model.entity.Curso;
import com.ortega.springcloud.msvc.cursos.model.pojo.Usuario;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listCursos();
    Optional<Curso> getById(Long id);
    Curso saveCurso(Curso curso);
    void deleteCurso(Long id);


    Optional<Usuario> addUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> createUsuario(Usuario usuario, Long cursoId);

    //remove relationship with Curso, doesn't delete the user
    Optional<Usuario> removeUsuario(Usuario usuario, Long cursoId);
}
