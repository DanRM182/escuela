package com.dan.escuela.mappers;

import com.dan.escuela.dto.datos.DatosGrupo;
import com.dan.escuela.dto.horarios.HorarioRequest;
import com.dan.escuela.dto.horarios.HorarioResponse;
import com.dan.escuela.entities.Grupo;
import com.dan.escuela.entities.Horario;
import com.dan.escuela.enums.DiaSemana;
import com.dan.escuela.utils.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HorarioMapper implements CommonMapper<HorarioRequest, HorarioResponse, Horario> {
    @Override
    public Horario requestAEntidad(HorarioRequest request) {
        return request != null ?
                Horario.builder()
                        .horaInicio(request.horaInicio())
                        .horaFin(request.horaFin())
                        .build() : null;
    }

    public Horario requestAEntidad(HorarioRequest request, DiaSemana diaSemana, Grupo grupo) {
        if(request == null) return null;

        Horario horario = requestAEntidad(request);

        horario.asignarGrupoDiaSemana(grupo, diaSemana);

        return horario;
    }

    @Override
    public HorarioResponse entidadAResponse(Horario entidad) {
        if(entidad == null || entidad.getDiaSemana() == null)
            return null;

        DatosGrupo grupo = MapperUtils.entidadAObjetoDato(entidad, Horario::getGrupo, MapperUtils::entidadADatosGrupo);

        return new HorarioResponse(
                entidad.getId(),
                grupo,
                MapperUtils.entidadADatosHorario(entidad));
    }
}
