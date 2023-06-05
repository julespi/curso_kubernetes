package com.julespi.springcloud.msvc.cursos.clients;

import com.julespi.springcloud.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1 - @FeignClient(name = "msvc-usuarios", url = "localhost:8001")  // msvc-cursos local y msvc-usuarios dockerizado
@FeignClient(name = "msvc-usuarios", url = "${msvc.usuarios.url}")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/")
    Usuario crear(@RequestBody Usuario usuario);

    @GetMapping("/usuarios")
    List<Usuario> listarPorIds(@RequestParam List<Long> ids);
}
