package com.dan.escuela.dto.grupos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

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
        @Size(min = 1, max = 20, message = "El periodo debe tener entre 1 y 20 caracteres")
        @Schema(description = "Periodo del grupo", example = "2026-01")
        String periodo
) { }
