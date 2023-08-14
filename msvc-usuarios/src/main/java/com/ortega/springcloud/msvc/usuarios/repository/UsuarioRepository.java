package com.ortega.springcloud.msvc.usuarios.repository;

import com.ortega.springcloud.msvc.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}