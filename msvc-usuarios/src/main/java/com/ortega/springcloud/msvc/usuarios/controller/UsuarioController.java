package com.ortega.springcloud.msvc.usuarios.controller;

import com.ortega.springcloud.msvc.usuarios.model.entity.Usuario;
import com.ortega.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public List<Usuario> listUsuarios(){
        return usuarioService.usuarioList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.getById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
        }catch (DataIntegrityViolationException e){
            if(e.getMessage().contains("UK_kfsp0s1tflm1cwlj8idhqsad0")){
                return ResponseEntity.badRequest().body("Email already exists");
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editUsuario(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> optional = usuarioService.getById(id);
        if(optional.isPresent()){
            Usuario usuarioDB = optional.get();
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuarioDB));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Long id){
        Optional<Usuario> optional = usuarioService.getById(id);
        if(optional.isPresent()){
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
