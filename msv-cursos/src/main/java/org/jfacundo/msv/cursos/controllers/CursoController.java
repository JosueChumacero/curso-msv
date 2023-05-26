package org.jfacundo.msv.cursos.controllers;

import org.jfacundo.msv.cursos.models.entity.Curso;
import org.jfacundo.msv.cursos.services.CursoSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {

    @Autowired
    private CursoSercive service;

    @GetMapping
    public List<Curso> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable long id){
        Optional<Curso> curso = service.porId(id);
        return curso.isPresent()?ResponseEntity.ok().body(curso.get()):
                ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso crear(@RequestBody Curso curso){
        return service.guardar(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso,@PathVariable long id){
        Optional<Curso> CursoOptional = service.porId(id);
        if (CursoOptional.isPresent()){
            Curso cursoBD = CursoOptional.get();
            cursoBD.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cursoBD));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        Optional<Curso> curso = service.porId(id);
        if (curso.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
