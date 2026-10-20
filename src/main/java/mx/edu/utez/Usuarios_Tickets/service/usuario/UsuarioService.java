package mx.edu.utez.Usuarios_Tickets.service.usuario;

import mx.edu.utez.Usuarios_Tickets.exception.CustomException.ResourceNotFoundException;
import mx.edu.utez.Usuarios_Tickets.model.usuario.Usuario;
import mx.edu.utez.Usuarios_Tickets.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario desactivarUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));

        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}