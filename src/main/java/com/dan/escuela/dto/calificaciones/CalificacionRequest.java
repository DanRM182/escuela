package com.dan.escuela.dto.calificaciones;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Datos necesarios para registrar/actualizar una calificación")
public record CalificacionRequest(
        @Schema(description = "ID de inscripción", example = "1")
        @NotNull(message = "El ID de la inscripción es requerido")
        @Positive(message = "El ID de la inscripción debe ser positivo")
        Long idInscripcion,

        @Schema(description = "ID de inscripción", example = "1")
        @NotNull(message = "El ID de la inscripción es requerido")
        @Min(value = 0, message = "La calificación debe ser mínimo 0")
        @Max(value = 10, message = "La calificación debe ser máximo 10")
        BigDecimal calificacion
) { }