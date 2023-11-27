package com.sistema.examenes.repositorios;

import com.sistema.examenes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsername(String username);
    public Usuario findByUsernameOrEmail(String username, String email);
    public Usuario findByTokenPassword(String tokenPassword);

}
