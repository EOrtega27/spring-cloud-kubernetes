package com.ortega.springcloud.msvc.cursos.client;

import com.ortega.springcloud.msvc.cursos.model.pojo.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "msvc-usuarios", url = "localhost:8001")
public interface UsuarioClientRest {
    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id);

    @PostMapping("/")
    public Usuario saveUsuario(@RequestBody Usuario usuario);
}
