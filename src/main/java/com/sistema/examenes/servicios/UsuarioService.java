package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UsuarioService  {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);

    void save(Usuario usuario);
}