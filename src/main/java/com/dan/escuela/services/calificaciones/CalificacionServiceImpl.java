package com.dan.escuela.services.calificaciones;

import com.dan.escuela.dto.calificaciones.CalificacionRequest;
import com.dan.escuela.dto.calificaciones.CalificacionResponse;
import com.dan.escuela.entities.Calificacion;
import com.dan.escuela.entities.Inscripcion;
import com.dan.escuela.mappers.CalificacionMapper;
import com.dan.escuela.repositories.CalificacionRepository;
import com.dan.escuela.repositories.InscripcionRepository;
import com.dan.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class CalificacionServiceImpl implements CalificacionService {
    private final CalificacionRepository calificacionRepository;
    private final InscripcionRepository inscripcionRepository;
    private final CalificacionMapper calificacionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CalificacionResponse> listar() {
        log.info("Listando todas las inscripciones");

        return calificacionRepository.findAll().stream()
                .map(calificacionMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CalificacionResponse obtenerPorId(Long id) {
        log.info("Buscando calificación por ID");

        return calificacionMapper.entidadAResponse(obtenerCalifcacion(id));
    }

    @Override
    public CalificacionResponse registrar(CalificacionRequest request) {
        log.info("Registrando nueva calificación...");

        validarCalificacionUnica(request);

        Inscripcion inscripcion = obtenerInscripcion(request.idInscripcion());

        Calificacion calificacion = calificacionMapper.requestAEntidad(request, inscripcion);

        calificacionRepository.save(calificacion);

        log.info("Nueva calificación con ID {} registrada.", calificacion.getId());

        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public CalificacionResponse actualizar(CalificacionRequest request, Long id) {
        Calificacion calificacion = obtenerCalifcacion(id);

        log.info("Actualizando calificación con id: {}", id);

        calificacion.asignarNuevaCalificacion(request.calificacion());

        log.info("Calificación con ID {} actualizada", id);

        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public void eliminar(Long id) {
        Calificacion calificacion = obtenerCalifcacion(id);

        log.info("Eliminando calificación con id: {}", id);

        calificacionRepository.delete(calificacion);

        log.info("Calificación con id {} eliminada correctamente", id);
    }

    private Calificacion obtenerCalifcacion(Long id) {


        return ServiceUtils.obtenerEntidadOException(calificacionRepository, id, Calificacion.class);
    }

    private Inscripcion obtenerInscripcion(Long id) {
        return ServiceUtils.obtenerEntidadOException(inscripcionRepository, id, Inscripcion.class);
    }

    private void validarCalificacionUnica(CalificacionRequest request) {
        log.info("Validando calificación única...");

        if(calificacionRepository.existsByInscripcionId(request.idInscripcion()))
            throw new IllegalArgumentException("La inscripción con ID: " +
                    request.idInscripcion() + " ya tiene calificación asignada.");
    }
}
