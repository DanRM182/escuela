package com.dan.escuela.dto.horarios;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Datos necesarios para registrar y/o actualizar un horario")
public record HorarioRequest(
        @Schema(description = "ID de grupo vinculado", example = "1")
        @NotNull(message = "El ID del grupo es requerido")
        @Positive(message = "El ID del grupo debe ser positivo")
        Long idGrupo,

        @NotBlank(message = "El día es requerido")
        @Size(min = 1, max = 15, message = "El día debe tener entre 1 y 15 caracteres")
        @Schema(description = "Día", example = "Lunes")
        String dia,

        @NotBlank(message = "La hora de inicio es requerida")
        @Size(min = 5, max = 5, message = "La hora de inicio debe tener 5 caracteres")
        @Pattern(
                regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$",
                message = "La hora de inicio debe tener el formato HH:mm"
        )
        @Schema(description = "Hora de inicio", example = "08:00")
        String horaInicio,

        @NotBlank(message = "La hora de finalización es requerida")
        @Size(min = 5, max = 5, message = "La hora de finalización debe tener 5 caracteres")
        @Pattern(
                regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$",
                message = "La hora de fin debe tener el formato HH:mm"
        )
        @Schema(description = "Hora de finalización", example = "10:00")
        String horaFin
) {
}
