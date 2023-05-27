package org.jfacundo.msv.usuarios.repositories;

import org.jfacundo.msv.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario , Long> {

    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
