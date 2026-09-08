package com.dan.escuela.mappers;

import com.dan.escuela.dto.cursos.CursoRequest;
import com.dan.escuela.dto.cursos.CursoResponse;
import com.dan.escuela.dto.datos.DatosCurso;
import com.dan.escuela.entities.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper implements CommonMapper<CursoRequest, CursoResponse, Curso> {
    @Override
    public Curso requestAEntidad(CursoRequest request) {
        if(request == null) return null;

        return Curso.builder()
                .nombre(request.nombre().trim())
                .descripcion(request.descripcion())
                .creditos(request.creditos())
                .build();
    }

    @Override
    public CursoResponse entidadAResponse(Curso entidad) {
        if(entidad == null) return null;

        return new CursoResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDescripcion() != null ?
                        entidad.getDescripcion(): "Sin descripción",
                entidad.getCreditos());
    }

    public DatosCurso entidadADatosCurso(Curso entidad) {
        if(entidad == null) return null;

        return new DatosCurso(
                entidad.getNombre(),
                entidad.getDescripcion() != null ?
                        entidad.getDescripcion(): "Sin descripción",
                entidad.getCreditos());
    }
}
