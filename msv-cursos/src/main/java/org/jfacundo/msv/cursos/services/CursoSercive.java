package org.jfacundo.msv.cursos.services;

import org.jfacundo.msv.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoSercive {

    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
}
