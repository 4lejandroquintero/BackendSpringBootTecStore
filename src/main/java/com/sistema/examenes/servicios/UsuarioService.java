package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UsuarioService extends JpaRepository<Usuario, Long> {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public Usuario obtenerUsuarioWithUsernameOrEmail(String usernameOrEmail);

    public Usuario obtenerUsuarioByTokenPassword(String tokenPassword);

    public void eliminarUsuario(Long usuarioId);
}
