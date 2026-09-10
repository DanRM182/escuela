package com.dan.escuela.mappers;

import com.dan.escuela.dto.datos.DatosCurso;
import com.dan.escuela.dto.datos.DatosMaestro;
import com.dan.escuela.dto.maestros.MaestroRequest;
import com.dan.escuela.dto.maestros.MaestroResponse;
import com.dan.escuela.entities.Grupo;
import com.dan.escuela.entities.Maestro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MaestroMapper implements CommonMapper<MaestroRequest, MaestroResponse, Maestro> {
    private final CursoMapper cursoMapper;

    @Override
    public Maestro requestAEntidad(MaestroRequest request) {
        return request != null ?
                Maestro.builder()
                    .nombre(request.nombre().trim())
                    .apellidoPaterno(request.apellidoPaterno().trim())
                    .apellidoMaterno(request.apellidoMaterno().trim())
                    .email(request.email().trim().toLowerCase())
                    .telefono(request.telefono().trim())
                    .build() : null;
    }

    @Override
    public MaestroResponse entidadAResponse(Maestro entidad) {
        if(entidad == null) return null;

        List<DatosCurso> cursos = entidadADatosCurso(entidad);

        return new MaestroResponse(
                entidad.getId(),
                entidad.obtenerNombreCompleto(entidad.getNombre(),
                        entidad.getApellidoPaterno(), entidad.getApellidoMaterno()),
                entidad.getEmail(),
                entidad.getTelefono(),
                cursos);
    }

    private List<DatosCurso> entidadADatosCurso(Maestro entidad) {
        return entidad != null ?
                entidad.getGrupos().stream()
                    .map(Grupo::getCurso)
                    .map(cursoMapper::entidadADatosCurso).toList() :
                    null;
    }

    public DatosMaestro entidadADatosMaestro(Maestro entidad) {
        return entidad != null ?
                new DatosMaestro(
                    String.join(" ",entidad.getNombre(), entidad.getApellidoPaterno(), entidad.getApellidoMaterno()),
                    entidad.getEmail(),
                    entidad.getTelefono()) : null;
    }
}