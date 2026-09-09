package com.dan.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Datos de una calificación")
public record DatosCalificacion(
        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        String curso,

        @Schema(description = "Periodo del curso", example = "Octubre")
        String periodo,

        @Schema(description = "Nombre del curso", example = "9.9")
        BigDecimal calificacion
) {
}
