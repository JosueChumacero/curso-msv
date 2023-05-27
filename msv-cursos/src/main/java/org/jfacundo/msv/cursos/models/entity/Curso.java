package org.jfacundo.msv.cursos.models.entity;

import lombok.Data;
import org.jfacundo.msv.cursos.models.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany (cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CursoUsuario> cursoUsuarios;

    @Transient
    private List<Usuario> usuarios;
    public Curso(){
        cursoUsuarios =  new ArrayList<>();
        usuarios = new ArrayList<>();
    }
    public void agregarCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.add(cursoUsuario);
    }
    public void eliminarCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.remove(cursoUsuario);
    }
}
