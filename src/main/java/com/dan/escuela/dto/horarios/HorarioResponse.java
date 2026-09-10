package com.dan.escuela.dto.horarios;


import com.dan.escuela.dto.datos.DatosGrupo;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de un horario")
public record HorarioResponse(
        @Schema(description = "ID del horario", example = "1")
        Long id,

        @Schema(description = "Datos del grupo asignado en el horario")
        DatosGrupo grupo,

        @Schema(description = "Día, Hora de inicio y fin", example = "Lunes 08:00 - 10:00")
        String horario
) { }
