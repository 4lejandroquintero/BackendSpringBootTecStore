package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;

import java.util.Optional;
import java.util.Set;

public interface UsuarioService  {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);

    void save(Usuario usuario);

    public Usuario obtenerUsuarioPorNombre(String nombreUsuario);

    public String obtenerPreguntaSecreta(String username);

    public void cambiarClave(String nombreUsuario, String nuevaClave);

    public boolean validarPreguntaRespuesta(String nombreUsuario, String preguntaSecreta, String respuestaSecreta);

    public Optional<Usuario> recuperarUsuario(String preguntaSecreta, String respuestaSecreta);
}