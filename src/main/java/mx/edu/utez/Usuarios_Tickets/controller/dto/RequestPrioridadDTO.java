package mx.edu.utez.Usuarios_Tickets.controller.dto;

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
public class RequestPrioridadDTO {

    @NotNull(message = "La nueva prioridad es obligatoria (BAJA, MEDIA, ALTA)")
    private PrioridadTicket nuevaPrioridad;
}