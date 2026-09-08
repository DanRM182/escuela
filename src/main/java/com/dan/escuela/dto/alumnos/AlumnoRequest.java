package com.dan.escuela.dto.alumnos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Información de un alumno")
public record AlumnoRequest(
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        @Schema(description = "Nombre del alumno", example = "Juan Gabriel")
        String nombre,

        @NotBlank(message = "El apellido paterno es requerido")
        @Size(min = 2, max = 50, message = "El apellido paterno debe tener entre 2 y 50 caracteres")
        @Schema(description = "Apellido paterno del alumno", example = "Gónzales")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es requerido")
        @Size(min = 2, max = 50, message = "El apellido materno debe tener entre 2 y 50 caracteres")
        @Schema(description = "Apellido materno del alumno", example = "Pérez")
        String apellidoMaterno
) { }
