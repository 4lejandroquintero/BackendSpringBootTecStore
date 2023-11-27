package com.sistema.examenes.controladores;

import com.sistema.examenes.dto.ChangePasswordDTO;
import com.sistema.examenes.dto.EmailValuesDTO;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.servicios.EmailService;
import com.sistema.examenes.servicios.UsuarioService;
import com.sistema.examenes.servicios.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.security.enterprise.credential.Password;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/email-password")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

   /* @GetMapping("/email/send")
    public ResponseEntity<?> sendEmail() {
        emailService.sendEmail();
        return new ResponseEntity("Correo enviado con exito", HttpStatus.OK);
    }*/

    @Value("${spring.mail.username}")
    private String mailFrom;

    private static final String subject = "Cambio de contraseña";
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @PostMapping("send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) {
        Optional<Usuario> usuarioOpt = Optional.ofNullable(usuarioService.obtenerUsuarioWithUsernameOrEmail(dto.getMailTo()));
        if (!usuarioOpt.isPresent())
            return new ResponseEntity("No existe el usuario en la BD", HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioOpt.get();
        dto.setMailFrom(mailFrom);
        dto.setMailTo(usuario.getEmail());
        dto.setSubject(subject);
        dto.setUserName(usuario.getUsername());
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        usuario.setTokenPassword(tokenPassword);
        usuarioService.save(usuario);

        emailService.sendEmail(dto);
        return new ResponseEntity("Te hemos enviado un correo", HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos mal puesto", HttpStatus.BAD_REQUEST);
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            return new ResponseEntity("Las contraseñasno coinciden", HttpStatus.BAD_REQUEST);
        Optional<Usuario> usuarioOpt = Optional.ofNullable(usuarioService.obtenerUsuarioByTokenPassword(dto.getTokenPassword()));
        if (!usuarioOpt.isPresent())
            return new ResponseEntity("No existe el usuario en la BD", HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(newPassword);
        usuario.setTokenPassword(null);
        usuarioService.save(usuario);

        return new ResponseEntity("Contraseña Actualizada", HttpStatus.OK);
    }
}
