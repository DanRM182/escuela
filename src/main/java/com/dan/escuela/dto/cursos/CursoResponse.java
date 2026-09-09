package com.dan.escuela.dto.cursos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Información de un curso")
public record CursoResponse(
        @Schema(description = "Id del curso", example = "1")
        Long id,

        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        String nombre,

        @Schema(description = "Descripción del curso", example = "Curso de cálculo integral")
        String descripcion,

        @Schema(description = "Créditos del curso", example = "5")
        Integer creditos
) { }
