package com.dan.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de un maestro")
public record DatosMaestro(
        @Schema(description = "Nombre completo del maestro", example = "Máximo Décimo Meridio")
        String nombre,

        @Schema(description = "Email del maestro", example = "test@test.com")
        String email,

        @Schema(description = "Teléfono del maestro", example = "5534565544")
        String telefono
) { }
