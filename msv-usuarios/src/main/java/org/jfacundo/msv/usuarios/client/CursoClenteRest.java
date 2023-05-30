package org.jfacundo.msv.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msv-cursos",  url = "localhost:8002")
public interface CursoClenteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}")
    public void eliminarCursoUsuario(@PathVariable Long id);
}
