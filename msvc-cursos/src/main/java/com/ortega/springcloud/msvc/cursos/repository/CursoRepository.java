package com.ortega.springcloud.msvc.cursos.repository;

import com.ortega.springcloud.msvc.cursos.model.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}