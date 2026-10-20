package mx.edu.utez.Usuarios_Tickets.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Usuarios_Tickets.model.ticket.PrioridadTicket;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCrearTicketDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria (BAJA, MEDIA, ALTA)")
    private PrioridadTicket prioridad;
}