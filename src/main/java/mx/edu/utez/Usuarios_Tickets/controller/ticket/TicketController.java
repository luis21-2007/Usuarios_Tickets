package mx.edu.utez.Usuarios_Tickets.controller.ticket;

import jakarta.validation.Valid;
import mx.edu.utez.Usuarios_Tickets.controller.dto.RequestCrearTicketDTO;
import mx.edu.utez.Usuarios_Tickets.controller.dto.RequestPrioridadDTO;
import mx.edu.utez.Usuarios_Tickets.model.ticket.Ticket;
import mx.edu.utez.Usuarios_Tickets.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/crear")
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody RequestCrearTicketDTO dto) {
        Ticket ticket = ticketService.crearTicket(dto.getIdUsuario(), dto.getDescripcion(), dto.getPrioridad());
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PostMapping("/{idTicket}/avanzar-estado")
    public ResponseEntity<Ticket> actualizarEstadoTicket(@PathVariable Long idTicket) {
        Ticket ticket = ticketService.actualizarEstado(idTicket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/{idTicket}/reasignar-prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad(
            @PathVariable Long idTicket,
            @Valid @RequestBody RequestPrioridadDTO dto) {
        Ticket ticket = ticketService.reasignarPrioridad(idTicket, dto.getNuevaPrioridad());
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long idUsuario) {
        List<Ticket> tickets = ticketService.obtenerTicketsUsuario(idUsuario);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}