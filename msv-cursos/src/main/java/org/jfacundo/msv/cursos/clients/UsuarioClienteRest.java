package org.jfacundo.msv.cursos.clients;

import org.jfacundo.msv.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "msv-usuarios", url = "localhost:8001")
public interface UsuarioClienteRest {

    @GetMapping("/{id}")
    Usuario detalle(@PathVariable long id);

    @PostMapping("/")
    Usuario  crear(@RequestBody Usuario usuario);
}
