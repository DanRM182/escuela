package com.dan.escuela.services.inscripciones;

import com.dan.escuela.dto.inscripciones.InscripcionRequest;
import com.dan.escuela.dto.inscripciones.InscripcionResponse;
import com.dan.escuela.entities.Alumno;
import com.dan.escuela.entities.Grupo;
import com.dan.escuela.entities.Inscripcion;
import com.dan.escuela.exceptions.EntidadRelacionadaException;
import com.dan.escuela.mappers.InscripcionMapper;
import com.dan.escuela.repositories.AlumnoRepository;
import com.dan.escuela.repositories.CalificacionRepository;
import com.dan.escuela.repositories.GrupoRepository;
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
public class InscripcionServiceImpl implements InscripcionService {
    private final InscripcionRepository inscripcionRepository;
    private final AlumnoRepository alumnoRepository;
    private final GrupoRepository grupoRepository;
    private final CalificacionRepository calificacionRepository;
    private final InscripcionMapper inscripcionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<InscripcionResponse> listar() {
        log.info("Listando todas las inscripciones");

        return inscripcionRepository.findAll().stream()
                .map(inscripcionMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InscripcionResponse obtenerPorId(Long id) {
        log.info("Buscando inscripción por ID");

        return inscripcionMapper.entidadAResponse(obtenerInscripcion(id));
    }

    @Override
    public InscripcionResponse registrar(InscripcionRequest request) {
        log.info("Registrando nueva inscripción...");

        validarInscripcionUnica(request);

        Alumno alumno = obtenerAlumno(request);

        Grupo grupo = obtenerGrupo(request);

        Inscripcion inscripcion = inscripcionMapper.requestAEntidad(request, alumno, grupo);

        inscripcionRepository.save(inscripcion);

        log.info("Nueva inscripción con ID {} registrada.", inscripcion.getId());

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public InscripcionResponse actualizar(InscripcionRequest request, Long id) {
        Inscripcion inscripcion = obtenerInscripcion(id);

        log.info("Actualizando inscripción con id: {}", id);

        validarCambioInscripcionUnica(request, id);

        Alumno alumno = obtenerAlumno(request);

        Grupo grupo = obtenerGrupo(request);

        inscripcion.actualizar(alumno, grupo);

        log.info("Inscripción con ID {} actualizada", id);

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public void eliminar(Long id) {
        Inscripcion inscripcion = obtenerInscripcion(id);

        log.info("Eliminando inscripción con id: {}", id);

        if(calificacionRepository.existsByInscripcionId(id))
            throw new EntidadRelacionadaException(
                    "No se puede eliminar la inscripción ya que tiene calificación asignada");

        inscripcionRepository.delete(inscripcion);

        log.info("Inscripción con ID {} eliminada correctamente", id);
    }

    private Inscripcion obtenerInscripcion(Long id) {
        return ServiceUtils.obtenerEntidadOException(inscripcionRepository, id, Inscripcion.class);
    }

    private Alumno obtenerAlumno(InscripcionRequest request) {
        return ServiceUtils.obtenerEntidadOException(alumnoRepository, request.idAlumno(), Alumno.class);
    }

    private Grupo obtenerGrupo(InscripcionRequest request) {
        return ServiceUtils.obtenerEntidadOException(grupoRepository, request.idGrupo(), Grupo.class);
    }

    private void validarInscripcionUnica(InscripcionRequest request) {
        log.info("Validando inscripción única...");

        if(inscripcionRepository.existsByAlumnoIdAndGrupoId(request.idAlumno(), request.idGrupo()))
            throw new IllegalArgumentException("Ya existe el alumno con ID " + request.idAlumno()
            + " inscrito al grupo con ID " + request.idGrupo());
    }

    private void validarCambioInscripcionUnica(InscripcionRequest request, Long id) {
        log.info("Validando cambio de inscripción");

        if(inscripcionRepository.existsByAlumnoIdAndGrupoIdAndIdNot(request.idAlumno(), request.idGrupo(), id))
            throw new IllegalArgumentException("Ya existe el alumno con ID " + request.idAlumno()
                    + " inscrito al grupo con ID " + request.idGrupo());
    }
}