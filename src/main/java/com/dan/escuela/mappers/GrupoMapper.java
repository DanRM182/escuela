package com.dan.escuela.mappers;

import com.dan.escuela.dto.datos.*;
import com.dan.escuela.dto.grupos.GrupoRequest;
import com.dan.escuela.dto.grupos.GrupoResponse;
import com.dan.escuela.entities.*;
import com.dan.escuela.utils.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GrupoMapper implements CommonMapper<GrupoRequest, GrupoResponse, Grupo> {
    private final CursoMapper cursoMapper;
    private final MaestroMapper maestroMapper;
    private final AulaMapper aulaMapper;

    @Override
    public Grupo requestAEntidad(GrupoRequest request) {
        return request != null ?
                Grupo.builder().periodo(request.periodo()).build() : null;
    }

    public Grupo requestAEntidad(GrupoRequest request, Curso curso, Maestro maestro, Aula aula) {
        if(request == null) return null;

        Grupo grupo = requestAEntidad(request);

        grupo.asignarDatosOtrasEntidades(curso, maestro, aula);

        return grupo;
    }

    @Override
    public GrupoResponse entidadAResponse(Grupo entidad) {
        if (entidad == null || entidad.getCurso() == null
                || entidad.getMaestro() == null || entidad.getAula() == null) return null;

        DatosCurso curso = MapperUtils.entidadAObjetoDato(entidad, Grupo::getCurso, cursoMapper::entidadADatosCurso);

        DatosMaestro maestro = MapperUtils.entidadAObjetoDato(entidad, Grupo::getMaestro, maestroMapper::entidadADatosMaestro);

        DatosAula aula = MapperUtils.entidadAObjetoDato(entidad, Grupo::getAula, aulaMapper::entidadADatosAula);

        List<String> horarios = entidadADatosHorarios(entidad);

        return new GrupoResponse(
                entidad.getId(),
                curso,
                maestro,
                aula,
                horarios,
                entidad.getPeriodo()
        );
    }

    private List<String> entidadADatosHorarios(Grupo entidad) {
        return entidad != null ? entidad.getHorarios().stream().
                map(MapperUtils::entidadADatosHorario).toList() : List.of();
    }
}
