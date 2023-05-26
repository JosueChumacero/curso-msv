package org.jfacundo.msv.usuarios.controllers;

import org.jfacundo.msv.usuarios.models.entity.Usuario;
import org.jfacundo.msv.usuarios.services.UsuarioService;
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
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable long id){
        Optional<Usuario> usuario = service.porId(id);
        return usuario.isPresent()?ResponseEntity.ok().body(usuario.get()):
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public  ResponseEntity<?>  crear(@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasErrors()){
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable long id){
        if (result.hasErrors()){
            return getMapResponseEntity(result);
        }
        Optional<Usuario> usuarioOptional = service.porId(id);
        if (usuarioOptional.isPresent()){
            Usuario usuarioBD = usuarioOptional.get();
            usuarioBD.setCorreo(usuario.getCorreo());
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioBD));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        Optional<Usuario> usuario = service.porId(id);
        if (usuario.isPresent()){
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
