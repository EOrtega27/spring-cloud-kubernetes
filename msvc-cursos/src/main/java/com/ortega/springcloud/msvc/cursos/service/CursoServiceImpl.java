package com.ortega.springcloud.msvc.cursos.service;

import com.ortega.springcloud.msvc.cursos.client.UsuarioClientRest;
import com.ortega.springcloud.msvc.cursos.model.entity.Curso;
import com.ortega.springcloud.msvc.cursos.model.entity.CursoUsuario;
import com.ortega.springcloud.msvc.cursos.model.pojo.Usuario;
import com.ortega.springcloud.msvc.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService{

    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    UsuarioClientRest usuarioClient;

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

    @Override
    @Transactional
    public Optional<Usuario> addUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = cursoRepository.findById(cursoId);
        if(o.isPresent()){
            Usuario usuarioMsvc = usuarioClient.getUsuario(usuario.getId());

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuario.getId());

            curso.addCursoUsuario(cursoUsuario);

            cursoRepository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> createUsuario(Usuario usuario, Long cursoId) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> removeUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = cursoRepository.findById(cursoId);
        if(o.isPresent()){
            Usuario usuarioMsvc = usuarioClient.getUsuario(usuario.getId());

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuario.getId());

            curso.deleteCursoUsuario(cursoUsuario);

            cursoRepository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }
}
