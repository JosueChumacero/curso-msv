package org.jfacundo.msv.cursos.services;

import org.jfacundo.msv.cursos.clients.UsuarioClienteRest;
import org.jfacundo.msv.cursos.models.Usuario;
import org.jfacundo.msv.cursos.models.entity.Curso;
import org.jfacundo.msv.cursos.models.entity.CursoUsuario;
import org.jfacundo.msv.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoSercive{

    @Autowired
    private CursoRepository repository;

    @Autowired
    private UsuarioClienteRest usuarioClienteRest;


    @Override
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    public Optional<Curso> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> optionalCurso = repository.findById(cursoId);
        if (optionalCurso.isPresent()){
            Usuario usuarioMsv = usuarioClienteRest.detalle(usuario.getId());
            Curso curso = optionalCurso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsv.getId());

            curso.agregarCursoUsuario(cursoUsuario);
            repository.save(curso);
            return Optional.of(usuarioMsv);
        }
        return  Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> optionalCurso = repository.findById(cursoId);
        if (optionalCurso.isPresent()){
            Usuario usuarioNuevoMsv = usuarioClienteRest.crear(usuario);
            Curso curso = optionalCurso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMsv.getId());

            curso.agregarCursoUsuario(cursoUsuario);
            repository.save(curso);
            return Optional.of(usuarioNuevoMsv);
        }
        return  Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuari(Usuario usuario, Long cursoId) {
        Optional<Curso> optionalCurso = repository.findById(cursoId);
        if (optionalCurso.isPresent()){
            Usuario usuarioMsv = usuarioClienteRest.detalle(usuario.getId());
            Curso curso = optionalCurso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsv.getId());

            curso.eliminarCursoUsuario(cursoUsuario);
            repository.save(curso);
            return Optional.of(usuarioMsv);
        }
        return  Optional.empty();
    }
}
