package com.dan.escuela.dto.aulas;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de un aula")
public record AulaResponse(
        @Schema(description = "Id del aula", example = "1")
        Long id,

        @Schema(description = "Nombre del aula", example = "Benito Juarez")
        String nombre,

        @Schema(description = "Capacidad del aula", example = "5")
        Integer capacidad
        ) {
}
