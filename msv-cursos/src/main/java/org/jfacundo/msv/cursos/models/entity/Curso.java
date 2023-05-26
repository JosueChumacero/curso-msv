package org.jfacundo.msv.cursos.models.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
