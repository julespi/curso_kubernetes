package com.julespi.springcloud.msvc.cursos.services;

import com.julespi.springcloud.msvc.cursos.models.Usuario;
import com.julespi.springcloud.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Optional<Curso> porIdConUsuarios(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    void eliminarCursoUsuario(Long id);
    Optional<Usuario> asignarUsuario(Usuario usuarioToAdd, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuarioToCreate, Long cursoId);
    Optional<Usuario> desasignarUsuario(Usuario usuarioToRemove, Long cursoId);

}
