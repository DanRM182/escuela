package com.dan.escuela.dto.grupos;

import com.dan.escuela.dto.datos.DatosAula;
import com.dan.escuela.dto.datos.DatosCurso;
import com.dan.escuela.dto.datos.DatosMaestro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Información de un grupo")
public record GrupoResponse(
        @Schema(description = "ID del grupo", example = "1")
        Long id,

        @Schema(description = "Datos del curso del grupo")
        DatosCurso curso,

        @Schema(description = "Datos del maestro del grupo")
        DatosMaestro maestro,

        @Schema(description = "Datos del aula del grupo")
        DatosAula aula,

        @Schema(description = "Datos de los horarios del grupo")
        List<String> horarios,

        @Schema(description = "Periodo del grupo")
        String periodo
) { }
