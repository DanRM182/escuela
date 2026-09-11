package com.dan.escuela.utils;

import com.dan.escuela.dto.datos.DatosAlumno;
import com.dan.escuela.dto.datos.DatosGrupo;
import com.dan.escuela.entities.Alumno;
import com.dan.escuela.entities.Grupo;
import com.dan.escuela.entities.Horario;

import java.util.function.Function;

public class MapperUtils {
    public static <T, E, D> D entidadAObjetoDato(T entidad, Function<T, E> obtenerRelacion, Function<E, D> mapper) {
        if (entidad == null) return null;

        E objeto = obtenerRelacion.apply(entidad);

        return objeto != null ? mapper.apply(objeto) : null;
    }

    public static String entidadADatosHorario(Horario entidad) {
        return entidad != null ?
                entidad.horarioConcatenado(entidad.getDiaSemana().getDescripcion(),
                    entidad.getHoraInicio(), entidad.getHoraFin()) : null;
    }

    public static DatosGrupo entidadADatosGrupo(Grupo entidad) {
        return entidad != null ?
                new DatosGrupo(
                        entidad.getCurso().getNombre(),
                        String.join(" ", entidad.getMaestro().getNombre(),
                                entidad.getMaestro().getApellidoPaterno(), entidad.getMaestro().getApellidoMaterno()),
                        entidad.getAula().getNombre(),
                        entidad.getPeriodo()) : null;
    }

    public static DatosAlumno entidadADatosAlumno(Alumno entidad) {
        return entidad != null ?
                new DatosAlumno(
                        entidad.obtenerNombreCompleto(
                                entidad.getNombre(),
                                entidad.getApellidoPaterno(),
                                entidad.getApellidoMaterno()),
                        entidad.getMatricula(),
                        entidad.getEmail(),
                        StringCustomUtils.localDateAString(
                                entidad.getFechaIngreso())) : null;
    }
}
