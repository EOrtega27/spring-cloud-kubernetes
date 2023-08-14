package com.ortega.springcloud.msvc.usuarios.services;

import com.ortega.springcloud.msvc.usuarios.model.entity.Usuario;
import com.ortega.springcloud.msvc.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> usuarioList() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> getById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
