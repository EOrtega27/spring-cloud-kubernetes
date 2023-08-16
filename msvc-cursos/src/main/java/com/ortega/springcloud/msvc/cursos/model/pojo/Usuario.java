package com.ortega.springcloud.msvc.cursos.model.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String password;
}
