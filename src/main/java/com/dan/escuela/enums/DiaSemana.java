package com.dan.escuela.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.dan.escuela.utils.StringCustomUtils;
import com.dan.escuela.exceptions.RecursoNoEncontradoException;
@RequiredArgsConstructor
@Getter
public enum DiaSemana {
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miércoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sábado");

    private final String descripcion;

    public static DiaSemana obtenerDiaSemanaPorDescripcion(String descripcion) {
        StringCustomUtils.validarNoVacio(descripcion, "La descripción es requerida");

        String descripcionNormalizada = StringCustomUtils.quitarAcentos(descripcion);

        for(DiaSemana diaSemana : values()) {
            if(StringCustomUtils.quitarAcentos(diaSemana.descripcion).equalsIgnoreCase(descripcionNormalizada))
                return diaSemana;
        }

        throw new RecursoNoEncontradoException("No existe un día de la semana con la descripción: " + descripcion);
    }
}

