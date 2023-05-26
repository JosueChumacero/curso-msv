package org.jfacundo.msv.cursos.controllers;

import org.jfacundo.msv.cursos.models.entity.Curso;
import org.jfacundo.msv.cursos.services.CursoSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso,BindingResult result){
        if (result.hasErrors()){
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso,BindingResult result,@PathVariable long id){
        if (result.hasErrors()){
            return getMapResponseEntity(result);
        }
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

    private static ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String>  errores= new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(),"El campo: " + err.getField()+ " " + err.getDefaultMessage() );
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
