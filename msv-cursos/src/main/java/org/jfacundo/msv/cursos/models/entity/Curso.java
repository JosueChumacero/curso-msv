package org.jfacundo.msv.cursos.models.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column (unique = true)
    private String nombre;
}
