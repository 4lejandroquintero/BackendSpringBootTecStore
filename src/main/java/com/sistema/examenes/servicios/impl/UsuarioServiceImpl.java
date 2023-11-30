package com.sistema.examenes.servicios.impl;

import com.sistema.examenes.excepcion.UsuarioFoundException;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import com.sistema.examenes.repositorios.RolRepository;
import com.sistema.examenes.repositorios.UsuarioRepository;
import com.sistema.examenes.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public void save(Usuario usuario) {

    }



    @Override
    public String obtenerPreguntaSecreta(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        return usuario != null ? usuario.getPreguntaSecreta() : null;
    }

    @Override
    public Optional<Usuario> recuperarUsuario(String preguntaSecreta, String respuestaSecreta) {
        return Optional.empty();
    }

    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        return usuarioRepository.findByUsername(nombreUsuario);
    }


    public boolean validarPreguntaRespuesta(String nombreUsuario, String preguntaSecreta, String respuestaSecreta) {
        Usuario usuario = obtenerUsuarioPorNombre(nombreUsuario);

        return usuario != null && preguntaSecreta.equals(usuario.getPreguntaSecreta()) && respuestaSecreta.equals(usuario.getRespuestaSecreta());
    }

    public void cambiarClave(String nombreUsuario, String nuevaClave) {
        Usuario usuario = obtenerUsuarioPorNombre(nombreUsuario);

        if (usuario != null) {
            usuario.setPassword(nuevaClave);
            usuarioRepository.save(usuario);
        }
    }
}