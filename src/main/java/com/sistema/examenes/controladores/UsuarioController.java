package com.sistema.examenes.controladores;

import com.sistema.examenes.modelo.RecuperarClaveRequest;
import com.sistema.examenes.modelo.Rol;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import com.sistema.examenes.repositorios.UsuarioRepository;
import com.sistema.examenes.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");

        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        if (!usuario.isAdmin()) {
            rol.setRolId(2L);
            rol.setRolNombre("NORMAL");
        } else {
            rol.setRolId(1L);
            rol.setRolNombre("ADMIN");
        }
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }


    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

    @PostMapping("/recuperar-usuario")
    public String recuperarClave(@RequestBody RecuperarClaveRequest request) {
        String nombreUsuario = request.getNombreUsuario();
        String preguntaSecreta = request.getPreguntaSecreta();
        String respuestaSecreta = request.getRespuestaSecreta();
        String nuevaClave = request.getNuevaClave();

        if (usuarioService.validarPreguntaRespuesta(nombreUsuario, preguntaSecreta, respuestaSecreta)) {
            usuarioService.cambiarClave(nombreUsuario, nuevaClave);
            return "Clave recuperada exitosamente.";
        } else {
            return "Validación de pregunta y respuesta fallida.";
        }
    }
}
