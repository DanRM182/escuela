package com.dan.escuela.dto.inscripciones;

import com.dan.escuela.dto.datos.DatosAlumno;
import com.dan.escuela.dto.datos.DatosGrupo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Información de un horario")
public record InscripcionResponse(
        @Schema(description = "ID de la inscripción", example = "1")
        Long id,

        @Schema(description = "Datos del alumno inscrito")
        DatosAlumno alumno,

        @Schema(description = "Datos del grupo al que se inscribió el alumno")
        DatosGrupo grupo,

        @Schema(description = "Calificación del alumno")
        BigDecimal calificacion,

        @Schema(description = "Fecha de inscripción del alumno")
        String fechaInscripcion
) { }
