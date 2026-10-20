package mx.edu.utez.Usuarios_Tickets.service.ticket;

import mx.edu.utez.Usuarios_Tickets.exception.CustomException.BadRequestException;
import mx.edu.utez.Usuarios_Tickets.exception.CustomException.ResourceNotFoundException;
import mx.edu.utez.Usuarios_Tickets.model.ticket.EstadoTicket;
import mx.edu.utez.Usuarios_Tickets.model.ticket.PrioridadTicket;
import mx.edu.utez.Usuarios_Tickets.model.ticket.Ticket;
import mx.edu.utez.Usuarios_Tickets.model.usuario.Usuario;
import mx.edu.utez.Usuarios_Tickets.repository.ticket.TicketRepository;
import mx.edu.utez.Usuarios_Tickets.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Ticket crearTicket(Long idUsuario, String descripcion, PrioridadTicket prioridad) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));

        if (!usuario.getActivo()) {
            throw new BadRequestException("El usuario está inactivo, no puede crear tickets");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(descripcion);
        ticket.setPrioridad(prioridad);
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    public Ticket actualizarEstado(Long idTicket) {
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new ResourceNotFoundException("El ticket no existe"));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("El ticket ya está cerrado y no se puede modificar");
        }

        if (ticket.getEstado() == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else if (ticket.getEstado() == EstadoTicket.EN_PROCESO) {
            ticket.setEstado(EstadoTicket.CERRADO);
        }

        return ticketRepository.save(ticket);
    }

    public Ticket reasignarPrioridad(Long idTicket, PrioridadTicket nuevaPrioridad) {
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new ResourceNotFoundException("El ticket no existe"));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("El ticket está cerrado y no se le puede cambiar la prioridad");
        }

        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerTicketsUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new ResourceNotFoundException("El usuario no existe");
        }
        return ticketRepository.findByUsuarioId(idUsuario);
    }
}