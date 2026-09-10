package com.dan.escuela.services.horarios;

import com.dan.escuela.dto.horarios.HorarioRequest;
import com.dan.escuela.dto.horarios.HorarioResponse;
import com.dan.escuela.entities.Grupo;
import com.dan.escuela.entities.Horario;
import com.dan.escuela.enums.DiaSemana;
import com.dan.escuela.mappers.GrupoMapper;
import com.dan.escuela.mappers.HorarioMapper;
import com.dan.escuela.repositories.GrupoRepository;
import com.dan.escuela.repositories.HorarioRepository;
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
public class HorarioServiceImpl implements HorarioService {
    private final HorarioRepository horarioRepository;
    private final HorarioMapper horarioMapper;
    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HorarioResponse> listar() {
        log.info("Listando todos los horarios");

        return horarioRepository.findAll().stream()
                .map(horarioMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HorarioResponse obtenerPorId(Long id) {
        log.info("Buscando horario por ID");

        return horarioMapper.entidadAResponse(obtenerHorario(id));
    }

    @Override
    public HorarioResponse registrar(HorarioRequest request) {
        log.info("Registrando nuevo horario...");

        validarHorario(request.horaInicio(), request.horaFin());

        DiaSemana diaSemana = DiaSemana.obtenerDiaSemanaPorDescripcion(request.dia());

        validarTraslapesHorarios(request, diaSemana);

        Grupo grupo = obtenerGrupo(request);

        Horario horario = horarioMapper.requestAEntidad(request, diaSemana, grupo);

        horarioRepository.save(horario);

        log.info("Nuevo horario con ID {} registrado.", horario.getId());

        return horarioMapper.entidadAResponse(horario);
    }

    @Override
    public HorarioResponse actualizar(HorarioRequest request, Long id) {
        Horario horario = obtenerHorario(id);

        log.info("Actualizando grupo con ID: {}", id);

        validarHorario(request.horaInicio(), request.horaFin());

        DiaSemana diaSemana = DiaSemana.obtenerDiaSemanaPorDescripcion(request.dia());

        validarCambiosTraslapesHorario(id, request, diaSemana);

        Grupo grupo = obtenerGrupo(request);

        horario.actualizar(grupo, diaSemana, request.horaInicio(), request.horaFin());

        log.info("Horario con ID {} actualizado correctamente", horario.getId());

        return horarioMapper.entidadAResponse(horario);
    }

    @Override
    public void eliminar(Long id) {
        Horario horario = obtenerHorario(id);

        log.info("Eliminando grupo con ID: {}", id);

        horarioRepository.delete(horario);

        log.info("Horario con ID {} eliminado correctamente", horario.getId());
    }

    private Horario obtenerHorario(Long id) {
        return ServiceUtils.obtenerEntidadOException(horarioRepository, id, Horario.class);
    }

    private Grupo obtenerGrupo(HorarioRequest request) {
        return ServiceUtils.obtenerEntidadOException(grupoRepository, request.idGrupo(), Grupo.class);
    }

    private void validarHorario(String horaInicio, String horaFin) {
    log.info("Validando que el horario de inicio sea menor que la hora fin...");
        if (horaInicio.compareTo(horaFin) >= 0) {
            throw new IllegalArgumentException(
                    "La hora de inicio debe ser menor que la hora de fin"
            );
        }
    }

    private void validarTraslapesHorarios(HorarioRequest request, DiaSemana diaSemana) {
        log.info("Validando que el nuevo horario no se traslape con los horarios del grupo, aula y periodo...");

        if(horarioRepository.validarTraslapesGrupoAulaPeriodo(request.idGrupo(),  diaSemana, request.horaInicio(), request.horaFin()))
            throw new IllegalArgumentException("El nuevo horario se traslapa con algún otro horario del grupo, del aula o del mismo periodo");
    }

    private void validarCambiosTraslapesHorario(Long id, HorarioRequest request, DiaSemana diaSemana) {
        log.info("Validando que el cambio de horario no se traslape con los horarios del grupo, aula y periodo...");

        if(horarioRepository.validarCambioTraslapesGrupoAulaPeriodo(id, request.idGrupo(), diaSemana, request.horaInicio(), request.horaFin()))
            throw new IllegalArgumentException("El nuevo horario se traslapa con algún otro horario del grupo, del aula o del mismo periodo");
    }
}
