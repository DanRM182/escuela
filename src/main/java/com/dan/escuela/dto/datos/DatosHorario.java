package com.dan.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de un horario")
public record DatosHorario(
        @Schema(description = "Día, Hora de inicio y fin", example = "Lunes 08:00 - 10:00")
        String horario
) { }