package com.dan.escuela.dto.horarios;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de un horario")
public record HorarioResponse(


        @Schema(description = "Día, Hora de inicio y fin", example = "Lunes 08:00 - 10:00")
        String horario
) { }
