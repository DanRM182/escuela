package com.dan.escuela.mappers;

import com.dan.escuela.dto.calificaciones.CalificacionRequest;
import com.dan.escuela.dto.calificaciones.CalificacionResponse;
import com.dan.escuela.dto.datos.DatosAlumno;
import com.dan.escuela.dto.datos.DatosCalificacion;
import com.dan.escuela.dto.datos.DatosInscripcion;
import com.dan.escuela.entities.Calificacion;
import com.dan.escuela.entities.Inscripcion;
import com.dan.escuela.utils.MapperUtils;
import com.dan.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalificacionMapper implements CommonMapper<CalificacionRequest, CalificacionResponse, Calificacion> {
    private final InscripcionMapper inscripcionMapper;
    @Override
    public Calificacion requestAEntidad(CalificacionRequest request) {
        return request != null ?
                Calificacion.builder()
                        .calificacion(request.calificacion())
                        .build(): null;
    }

    public Calificacion requestAEntidad(CalificacionRequest request, Inscripcion inscripcion) {
        if(request == null) return  null;

        Calificacion calificacion = requestAEntidad(request);

        calificacion.asignarInscripcion(inscripcion);

        return calificacion;
    }

    @Override
    public CalificacionResponse entidadAResponse(Calificacion entidad) {
        if(entidad == null || entidad.getInscripcion() == null) return  null;

        DatosInscripcion inscripcion = MapperUtils.entidadAObjetoDato(entidad,
                Calificacion::getInscripcion, inscripcionMapper::entidadADatosInscripcion);

        return new CalificacionResponse(
                entidad.getId(),
                inscripcion,
                entidad.getCalificacion(),
                StringCustomUtils.localDateAString(
                        entidad.getFechaRegistro()));
    }
}
