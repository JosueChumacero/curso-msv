package org.jfacundo.msv.cursos.controllers;

import feign.FeignException;
import org.jfacundo.msv.cursos.models.Usuario;
import org.jfacundo.msv.cursos.models.entity.Curso;
import org.jfacundo.msv.cursos.services.CursoSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class CursoController {

    @Autowired
    private CursoSercive service;

    @GetMapping
    public List<Curso> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable long id) {
        Optional<Curso> curso = service.porId(id);
        return curso.isPresent() ? ResponseEntity.ok().body(curso.get()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        Optional<Curso> CursoOptional = service.porId(id);
        if (CursoOptional.isPresent()) {
            Curso cursoBD = CursoOptional.get();
            cursoBD.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cursoBD));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id) {
        Optional<Curso> curso = service.porId(id);
        if (curso.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        try {
            Optional<Usuario> optionalUsuario = service.asignarUsuario(usuario, cursoId);
            if (optionalUsuario.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(optionalUsuario.get());
            }
            return ResponseEntity.notFound().build();
        } catch (FeignException.FeignClientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el usuario por el id o error en la comunicación: " + e.getMessage()));
        }
    }

    @PutMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        try {
            Optional<Usuario> optionalUsuario = service.crearUsuario(usuario, cursoId);
            if (optionalUsuario.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(optionalUsuario.get());
            }
            return ResponseEntity.notFound().build();
        } catch (FeignException.FeignClientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No se pudo crear el usuario o error en la comunicación: " + e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        try {
            Optional<Usuario> optionalUsuario = service.eliminarUsuari(usuario, cursoId);
            if (optionalUsuario.isPresent()) {
                return ResponseEntity.ok().body(optionalUsuario.get());
            }
            return ResponseEntity.notFound().build();
        } catch (FeignException.FeignClientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el usuario por el id o error en la comunicación: " + e.getMessage()));
        }
    }

    private static ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo: " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
