package com.dan.escuela.dto.inscripciones;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos necesarios para registrar/actualizar una inscripción")
public record InscripcionRequest(
        @Schema(description = "ID de alumno inscrito", example = "1")
        @NotNull(message = "El ID del alumno es requerido")
        @Positive(message = "El ID del alumno debe ser positivo")
        Long idAlumno,

        @Schema(description = "ID de grupo vinculado", example = "1")
        @NotNull(message = "El ID del grupo es requerido")
        @Positive(message = "El ID del grupo debe ser positivo")
        Long idGrupo
) { }
