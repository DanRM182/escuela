package com.dan.escuela.mappers;

import com.dan.escuela.dto.cursos.CursoRequest;
import com.dan.escuela.dto.cursos.CursoResponse;
import com.dan.escuela.dto.datos.DatosCurso;
import com.dan.escuela.entities.Curso;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CursoMapper implements CommonMapper<CursoRequest, CursoResponse, Curso> {
    @Override
    public Curso requestAEntidad(CursoRequest request) {
        return request != null ?
                Curso.builder()
                    .nombre(request.nombre().trim())
                    .descripcion(request.descripcion())
                    .creditos(request.creditos())
                    .build() : null;
    }

    @Override
    public CursoResponse entidadAResponse(Curso entidad) {
        return entidad != null ?
                new CursoResponse(
                    entidad.getId(),
                    entidad.getNombre(),
                    entidad.getDescripcion() != null ?
                            entidad.getDescripcion(): "Sin descripción",
                    entidad.getCreditos()) : null;
    }

    public DatosCurso entidadADatosCurso(Curso entidad) {
        return entidad != null ?
                new DatosCurso(
                    entidad.getNombre(),
                    entidad.getDescripcion() != null ?
                            entidad.getDescripcion(): "Sin descripción",
                    entidad.getCreditos()) : null;
    }
}
