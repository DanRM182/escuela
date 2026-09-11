package com.dan.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de una inscripción")
public record DatosInscripcion(
        @Schema(description = "Datos del alumno inscrito")
        DatosAlumno alumno,

        @Schema(description = "Datos del grupo")
        DatosGrupo grupo,

        @Schema(description = "Fecha de inscripción del alumno")
        String fechaInscripcion
) { }