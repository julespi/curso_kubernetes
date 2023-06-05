package com.julespi.springcloud.msvc.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 1 - @FeignClient(name = "msvc-cursos", url = "localhost:8002") // sin dockerizar
// 2 - @FeignClient(name = "msvc-cursos", url = "host.docker.internal:8002")  // dockerizando solo msvc-usuarios
@FeignClient(name = "msvc-cursos", url = "${msvc.cursos.url}")
public interface CursoClienteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}")
    void eliminarCursoUsuario(@PathVariable Long id);
}
