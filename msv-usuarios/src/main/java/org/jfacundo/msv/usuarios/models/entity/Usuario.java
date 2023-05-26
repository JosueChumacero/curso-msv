package org.jfacundo.msv.usuarios.models.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;

    @Column (unique = true)
    private String correo;

    @Column
    private String password;
}
