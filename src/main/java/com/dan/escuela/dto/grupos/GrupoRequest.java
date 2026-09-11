package com.dan.escuela.dto.grupos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Datos necesarios para registrar/actualizar un grupo")
public record GrupoRequest(
        @Schema(description = "ID de curso vinculado", example = "1")
        @NotNull(message = "El ID del curso es requerido")
        @Positive(message = "El ID del curso debe ser positivo")
        Long idCurso,

        @Schema(description = "ID de maestro vinculado", example = "1")
        @NotNull(message = "El ID del maestro es requerido")
        @Positive(message = "El ID del maestro debe ser positivo")
        Long idMaestro,

        @Schema(description = "ID de curso vinculado", example = "1")
        @NotNull(message = "El ID del curso es requerido")
        @Positive(message = "El ID del curso debe ser positivo")
        Long idAula,

        @NotBlank(message = "El periodo es requerido")
        @Pattern(
                regexp = "^2\\d{3}-(0[1-9]|1[0-2])$",
                message = "El periodo debe tener el formato YYYY-MM"
        )
        @Schema(description = "Periodo del grupo", example = "2026-01")
        String periodo
) { }
