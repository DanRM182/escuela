package com.dan.escuela.dto.cursos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Datos necesarios para registrar o actualizar un curso")
public record CursoRequest(
        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres")
        String nombre,

        @Schema(description = "Descripción del curso", example = "Curso de cálculo integral")
        @Size(max = 200, message = "La descripción debe tener máximo 200 caracteres")
        String descripcion,

        @Schema(description = "Créditos del curso", example = "5")
        @NotNull(message = "Los créditos son requeridos")
        @Min(value = 1, message = "Los créditos mínimos son 1")
        @Max(value = 10, message = "Los créditos máximos son 10")
        Integer creditos
) { }
