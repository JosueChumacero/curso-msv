package org.jfacundo.msv.usuarios.repositories;

import org.jfacundo.msv.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario , Long> {

}
