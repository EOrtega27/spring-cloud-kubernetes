package com.ortega.springcloud.msvc.usuarios.services;

import com.ortega.springcloud.msvc.usuarios.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> usuarioList();
    Optional<Usuario> getById(Long id);
    Usuario saveUsuario(Usuario usuario);
    void deleteUsuario(Long id);

}
