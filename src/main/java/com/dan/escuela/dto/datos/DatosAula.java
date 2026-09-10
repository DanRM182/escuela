package com.dan.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de un aula")
public record DatosAula(
        @Schema(description = "Nombre del aula", example = "Benito Juarez")
        String nombre,

        @Schema(description = "Capacidad del aula", example = "5")
        Integer capacidad
) { }