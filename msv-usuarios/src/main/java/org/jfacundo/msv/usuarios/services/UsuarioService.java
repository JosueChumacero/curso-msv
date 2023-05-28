package org.jfacundo.msv.usuarios.services;

import org.jfacundo.msv.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Optional<Usuario> porCorreo(String correo);
    boolean existePorCorreo(String correo);
    List<Usuario> listarPorIds(Iterable<Long> ids);
}
