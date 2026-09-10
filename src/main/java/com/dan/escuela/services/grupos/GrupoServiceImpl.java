package com.dan.escuela.services.grupos;

import com.dan.escuela.dto.grupos.GrupoRequest;
import com.dan.escuela.dto.grupos.GrupoResponse;
import com.dan.escuela.entities.Aula;
import com.dan.escuela.entities.Curso;
import com.dan.escuela.entities.Grupo;
import com.dan.escuela.entities.Maestro;
import com.dan.escuela.exceptions.EntidadRelacionadaException;
import com.dan.escuela.mappers.GrupoMapper;
import com.dan.escuela.repositories.*;
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
public class GrupoServiceImpl implements GrupoService {
    private final GrupoRepository grupoRepository;
    private final CursoRepository cursoRepository;
    private final MaestroRepository maestroRepository;
    private final AulaRepository aulaRepository;
    private final HorarioRepository horarioRepository;
    private final InscripcionRepository inscripcionRepository;
    private final GrupoMapper grupoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GrupoResponse> listar() {
        log.info("Listando todos los grupos");

        return grupoRepository.findAll().stream()
                .map(grupoMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoResponse obtenerPorId(Long id) {
        log.info("Buscando grupo por ID");

        return grupoMapper.entidadAResponse(obtenerGrupo(id));
    }

    @Override
    public GrupoResponse registrar(GrupoRequest request) {
        log.info("Registrando nuevo grupo");

        validarGrupoUnico(request);

        Curso curso = obtenerCurso(request);

        Maestro maestro = obtenerMaestro(request);

        Aula aula = obtenerAula(request);

        Grupo grupo = grupoMapper.requestAEntidad(request, curso, maestro, aula);

        grupoRepository.save(grupo);

        log.info("Nuevo grupo con ID {} registrado.", grupo.getId());

        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public GrupoResponse actualizar(GrupoRequest request, Long id) {
        Grupo grupo = obtenerGrupo(id);

        log.info("Actualizando grupo con id: {}", id);

        validarCambiosGrupoUnico(request, id);

        Curso curso = obtenerCurso(request);

        Maestro maestro = obtenerMaestro(request);

        Aula aula = obtenerAula(request);

        grupo.actualizar(curso, maestro, aula, request.periodo());

        log.info("Grupo con ID {} actualizado correctgamente", grupo.getId());

        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public void eliminar(Long id) {
        Grupo grupo = obtenerGrupo(id);

        log.info("Eliminando grupo con id: {}", id);

        if(horarioRepository.existsByGrupoId(id))
            throw new EntidadRelacionadaException("No se puede eliminar el grupo ya que tiene horarios asociados");

        if(inscripcionRepository.existsByGrupoId(id))
            throw new EntidadRelacionadaException("No se puede eliminar el grupo ya que tiene inscrpciones asociadas");

        grupoRepository.delete(grupo);

        log.info("Grupo con ID {} eliminado correctamente", grupo.getId());
    }

    private Grupo obtenerGrupo(Long id) {
        return ServiceUtils.obtenerEntidadOException(grupoRepository, id, Grupo.class);
    }

    private Curso obtenerCurso(GrupoRequest request) {
        return ServiceUtils.obtenerEntidadOException(cursoRepository, request.idCurso(), Curso.class);
    }

    private Maestro obtenerMaestro(GrupoRequest request) {
        return ServiceUtils.obtenerEntidadOException(maestroRepository, request.idMaestro(), Maestro.class);
    }

    private Aula obtenerAula(GrupoRequest request) {
        return ServiceUtils.obtenerEntidadOException(aulaRepository, request.idAula(), Aula.class);
    }

    private void validarGrupoUnico(GrupoRequest request) {
        log.info("Validando que no exista el grupo con mismo curso, maestro, aula y periodo...");

        if(grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodo(request.idCurso(),
                request.idMaestro(), request.idAula(), request.periodo()))
            throw new IllegalArgumentException("Ya existe un grupo con ID de curso: " + request.idCurso() +
                    " , ID maestro: " + request.idMaestro() + ", ID aula: " + request.idAula() + " y periodo: " +
                    request.periodo());
    }

    private void validarCambiosGrupoUnico(GrupoRequest request, Long id) {
        log.info("Validando cambios en grupo único y que el cambio cumpla las reglas:" +
                " No exista el grupo con mismo curso, maestro, aula y periodo...");

        if(grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodoAndIdNot(request.idCurso(),
                request.idMaestro(), request.idAula(), request.periodo(), id))
            throw new IllegalArgumentException("Ya existe un grupo con ID de curso: " + request.idCurso() +
                    " , ID maestro: " + request.idMaestro() + ", ID aula: " + request.idAula() + " y periodo: " +
                    request.periodo());
    }
}