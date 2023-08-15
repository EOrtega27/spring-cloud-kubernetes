package com.ortega.springcloud.msvc.cursos.service;

import com.ortega.springcloud.msvc.cursos.model.entity.Curso;
import com.ortega.springcloud.msvc.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService{

    @Autowired
    CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listCursos() {
        return cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> getById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
