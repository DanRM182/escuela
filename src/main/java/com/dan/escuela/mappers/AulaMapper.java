package com.dan.escuela.mappers;

import com.dan.escuela.dto.aulas.AulaRequest;
import com.dan.escuela.dto.aulas.AulaResponse;
import com.dan.escuela.entities.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper implements CommonMapper<AulaRequest, AulaResponse, Aula> {
    @Override
    public Aula requestAEntidad(AulaRequest request) {
        if(request == null) return null;

    return Aula.builder()
            .nombre(request.nombre().trim())
            .capacidad(request.capacidad())
            .build();
    }

    @Override
    public AulaResponse entidadAResponse(Aula entidad) {
        if(entidad == null) return null;

        return new AulaResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getCapacidad());
    }
}
