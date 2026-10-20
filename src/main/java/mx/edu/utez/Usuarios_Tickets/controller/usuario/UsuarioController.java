package mx.edu.utez.Usuarios_Tickets.controller.usuario;

import mx.edu.utez.Usuarios_Tickets.model.usuario.Usuario;
import mx.edu.utez.Usuarios_Tickets.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/{idUsuario}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.desactivarUsuario(idUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}