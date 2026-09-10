package com.dan.escuela.mappers;

import com.dan.escuela.dto.aulas.AulaRequest;
import com.dan.escuela.dto.aulas.AulaResponse;
import com.dan.escuela.dto.datos.DatosAula;
import com.dan.escuela.entities.Aula;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AulaMapper implements CommonMapper<AulaRequest, AulaResponse, Aula> {
    @Override
    public Aula requestAEntidad(AulaRequest request) {
        return request != null ?
                Aula.builder()
                    .nombre(request.nombre().trim())
                    .capacidad(request.capacidad())
                    .build() : null;
    }

    @Override
    public AulaResponse entidadAResponse(Aula entidad) {
        return entidad != null ?
                new AulaResponse(
                    entidad.getId(),
                    entidad.getNombre(),
                    entidad.getCapacidad()) : null;
    }

    public DatosAula entidadADatosAula(Aula entidad) {
        return entidad != null ?
                new DatosAula(
                entidad.getNombre(),
                entidad.getCapacidad()) : null;
    }
}
