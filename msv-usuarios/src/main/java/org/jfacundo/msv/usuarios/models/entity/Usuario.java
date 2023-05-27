package org.jfacundo.msv.usuarios.models.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table( name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    private String nombre;

    @Column (unique = true)
    @Email
    @NotBlank
    private String correo;

    @Column
    @NotBlank
    private String password;
}
