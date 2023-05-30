package org.jfacundo.msv.cursos.repositories;

import org.jfacundo.msv.cursos.models.entity.CursoUsuario;
import org.springframework.data.repository.CrudRepository;

public interface CursoUsuarioRepository extends CrudRepository<CursoUsuario, Long> {

    void deleteByUsuarioId(Long id);
}
