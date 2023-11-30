package com.sistema.examenes.repositorios;

import com.sistema.examenes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsername(String username);

    Optional<Usuario> findByPreguntaSecretaAndRespuestaSecreta(String preguntaSecreta, String respuestaSecreta);



}