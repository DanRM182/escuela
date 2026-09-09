package com.dan.escuela.dto.aulas;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Datos necesarios para registrar o actualizar un aula")
public record AulaRequest(
        @Schema(description = "Nombre del aula", example = "Benitp Juarez")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres")
        String nombre,

        @Schema(description = "Capacidad del aula", example = "5")
        @NotNull(message = "La capacidad es requerida")
        @Min(value = 1, message = "La capacidad mínima es 1")
        Integer capacidad
) { }
