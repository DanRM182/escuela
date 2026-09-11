package com.dan.escuela.mappers;

import com.dan.escuela.dto.datos.DatosAlumno;
import com.dan.escuela.dto.datos.DatosGrupo;
import com.dan.escuela.dto.datos.DatosInscripcion;
import com.dan.escuela.dto.inscripciones.InscripcionRequest;
import com.dan.escuela.dto.inscripciones.InscripcionResponse;
import com.dan.escuela.entities.Alumno;
import com.dan.escuela.entities.Grupo;
import com.dan.escuela.entities.Inscripcion;
import com.dan.escuela.utils.MapperUtils;
import com.dan.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InscripcionMapper implements CommonMapper<InscripcionRequest, InscripcionResponse, Inscripcion> {
    private final AlumnoMapper alumnoMapper;

    @Override
    public Inscripcion requestAEntidad(InscripcionRequest request) {
        return request != null ? Inscripcion.builder().build() : null;
    }

    public Inscripcion requestAEntidad(InscripcionRequest request, Alumno alumno, Grupo grupo) {
        if(request == null) return null;

        Inscripcion inscripcion = requestAEntidad(request);

        inscripcion.asignarAlumnoGrupo(alumno, grupo);

        return inscripcion;
    }

    @Override
    public InscripcionResponse entidadAResponse(Inscripcion entidad) {
        if(entidad == null || entidad.getGrupo() == null || entidad.getAlumno() == null)
            return null;

        DatosGrupo grupo = MapperUtils.entidadAObjetoDato(entidad, Inscripcion::getGrupo, MapperUtils::entidadADatosGrupo);

        DatosAlumno alumno = MapperUtils.entidadAObjetoDato(entidad, Inscripcion::getAlumno, alumnoMapper::entidadADatosAlumno);

        return new InscripcionResponse(
                entidad.getId(),
                alumno,
                grupo,
                entidad.getCalificacion() != null ?
                        entidad.getCalificacion().getCalificacion() : null,
                StringCustomUtils.localDateAString(
                        entidad.getFechaInscripcion()));
    }
    public DatosInscripcion entidadADatosInscripcion(Inscripcion entidad) {
        return entidad != null ?
                new DatosInscripcion(
                        MapperUtils.entidadAObjetoDato(entidad, Inscripcion::getAlumno, alumnoMapper::entidadADatosAlumno),
                        MapperUtils.entidadAObjetoDato(entidad, Inscripcion::getGrupo, MapperUtils::entidadADatosGrupo),
                        StringCustomUtils.localDateAString(
                                entidad.getFechaInscripcion())) : null;
    }
}
