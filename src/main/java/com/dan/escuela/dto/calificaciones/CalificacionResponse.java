package com.dan.escuela.dto.calificaciones;

import com.dan.escuela.dto.datos.DatosInscripcion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Información de una calificación")
public record CalificacionResponse(
        @Schema(description = "ID de la calificación", example = "1")
        Long id,

        @Schema(description = "Datos de inscripción del alumno")
        DatosInscripcion inscripcion,

        @Schema(description = "Calificación del alumno")
        BigDecimal calificacion,

        @Schema(description = "Fecha de inscripción del alumno")
        String fechaRegistro
) { }