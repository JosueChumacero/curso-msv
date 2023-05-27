package org.jfacundo.msv.cursos.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class Usuario {

    private long id;
    private String nombre;
    private String correo;
    private String password;
}
