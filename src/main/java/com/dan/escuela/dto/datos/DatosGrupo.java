package com.dan.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de un grupo")
public record DatosGrupo(
        @Schema(description = "Nombre de curso del grupo", example = "Matemáticas I")
        String curso,

        @Schema(description = "Nombre de maestro del grupo", example = "Laura Martínez Martínez")
        String maestro,

        @Schema(description = "Nombre del aula del grupo", example = "Aula 101")
        String aula,

        @Schema(description = "Periodo del grupo", example = "2025-1")
        String periodo
) { }