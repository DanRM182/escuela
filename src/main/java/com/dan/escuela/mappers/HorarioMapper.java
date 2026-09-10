package com.dan.escuela.mappers;

import com.dan.escuela.dto.datos.DatosHorario;
import com.dan.escuela.dto.datos.DatosMaestro;
import com.dan.escuela.dto.horarios.HorarioRequest;
import com.dan.escuela.dto.horarios.HorarioResponse;
import com.dan.escuela.entities.Horario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HorarioMapper implements CommonMapper<HorarioRequest, HorarioResponse, Horario> {
    @Override
    public Horario requestAEntidad(HorarioRequest request) {
        return null;
    }

    @Override
    public HorarioResponse entidadAResponse(Horario entidad) {
        return null;
    }

    public DatosHorario entidadADatosHorario(Horario entidad) {
        if(entidad == null) return null;

        return new DatosHorario(
                String.join(" ",entidad.getDiaSemana().getDescripcion(),
                        entidad.getHoraInicio(), "-", entidad.getHoraFin()));
    }
}
