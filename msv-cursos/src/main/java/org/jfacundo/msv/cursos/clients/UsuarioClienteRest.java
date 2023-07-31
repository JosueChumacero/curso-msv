package org.jfacundo.msv.cursos.clients;

import org.jfacundo.msv.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "msv-usuarios", url = "${msv.usuarios.url}")
public interface UsuarioClienteRest {

    @GetMapping("/{id}")
    Usuario detalle(@PathVariable long id);

    @PostMapping("/")
    Usuario  crear(@RequestBody Usuario usuario);

    @GetMapping("/usuarios-por-ids")
    List<Usuario> obtenerUsuariosPorId(@RequestParam Iterable<Long> ids);
}
