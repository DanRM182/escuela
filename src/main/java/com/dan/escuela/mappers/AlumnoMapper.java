package com.dan.escuela.mappers;

import com.dan.escuela.dto.alumnos.AlumnoRequest;
import com.dan.escuela.dto.alumnos.AlumnoResponse;
import com.dan.escuela.dto.datos.DatosAlumno;
import com.dan.escuela.dto.datos.DatosCalificacion;
import com.dan.escuela.entities.Alumno;
import com.dan.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
public class AlumnoMapper implements CommonMapper<AlumnoRequest, AlumnoResponse, Alumno> {
    @Override
    public Alumno requestAEntidad(AlumnoRequest request) {
        return request != null ?
                Alumno.builder()
                    .nombre(request.nombre().trim())
                    .apellidoPaterno(request.apellidoPaterno().trim())
                    .apellidoMaterno(request.apellidoMaterno().trim())
                    .build() : null;
    }

    public Alumno requestAEntidad(AlumnoRequest request, String email, String matricula) {
        if(request == null) return null;

        Alumno alumno = requestAEntidad(request);

        alumno.asignarDatosAcademicos(email, matricula);

        return alumno;
    }

    @Override
    public AlumnoResponse entidadAResponse(Alumno entidad) {
        if(entidad == null) return null;

        List<DatosCalificacion> calificaciones = entidadDatosACalificaciones(entidad);

        return new AlumnoResponse(
                entidad.getId(),
                entidad.obtenerNombreCompleto(
                        entidad.getNombre(),
                        entidad.getApellidoPaterno(),
                        entidad.getApellidoMaterno()),
                entidad.getEmail(),
                entidad.getMatricula(),
                StringCustomUtils.localDateAString(
                        entidad.getFechaIngreso()),
                calificaciones,
                entidad.calcularPromedio());
    }

    public DatosAlumno entidadADatosAlumno(Alumno entidad) {
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

    private List<DatosCalificacion> entidadDatosACalificaciones(Alumno entidad) {
        if(entidad == null || entidad.getInscripciones() == null || entidad.getInscripciones().isEmpty())
            return List.of();

        return entidad.getInscripciones().stream()
                .map(inscripcion -> new DatosCalificacion(
                        inscripcion.getGrupo().getCurso().getNombre(),
                        inscripcion.getGrupo().getPeriodo(),
                        inscripcion.getCalificacion() != null ? inscripcion.getCalificacion().getCalificacion() : null
                )).toList();
    }
}
