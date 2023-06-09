package org.jfacundo.msv.usuarios.services;

import org.jfacundo.msv.usuarios.client.CursoClenteRest;
import org.jfacundo.msv.usuarios.models.entity.Usuario;
import org.jfacundo.msv.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private CursoClenteRest clenteRest;

    @Override
    public List<Usuario> listar() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
        clenteRest.eliminarCursoUsuario(id);
    }
    @Override
    public Optional<Usuario> porCorreo(String correo) {
        return repository.findByCorreo(correo);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return repository.existsByCorreo(correo);
    }

    @Override
    public List<Usuario> listarPorIds(Iterable<Long> ids) {
        return (List<Usuario>) repository.findAllById(ids);
    }
}
