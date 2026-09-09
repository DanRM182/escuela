package com.dan.escuela.services.cursos;

import com.dan.escuela.dto.cursos.CursoRequest;
import com.dan.escuela.dto.cursos.CursoResponse;
import com.dan.escuela.entities.Curso;
import com.dan.escuela.exceptions.EntidadRelacionadaException;
import com.dan.escuela.mappers.CursoMapper;
import com.dan.escuela.repositories.CursoRepository;
import com.dan.escuela.repositories.GrupoRepository;
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
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    private final GrupoRepository grupoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar() {
        log.info("Listando todos los cursos");

        return cursoRepository.findAll().stream()
                .map(cursoMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse obtenerPorId(Long id) {
        log.info("Buscando curso por ID");

        return cursoMapper.entidadAResponse(obtenerCurso(id));
    }

    @Override
    public CursoResponse registrar(CursoRequest request) {
        log.info("Registrando nuevo curso...");

        validarNombreUnico(request);

        Curso curso = cursoMapper.requestAEntidad(request);

        cursoRepository.save(curso);

        log.info("Nuevo curso {} creado", curso.getNombre());

        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public CursoResponse actualizar(CursoRequest request, Long id) {
        Curso curso = obtenerCurso(id);

        log.info("Actualizando maestro con id: {}", id);

        validarCambioUnico(request, id);

        curso.actualizar(
                request.nombre(),
                request.descripcion(),
                request.creditos());

        log.info("Curso {} actualizado correctamente", curso.getNombre());

        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public void eliminar(Long id) {
        Curso curso = obtenerCurso(id);

        log.info("Eliminando curso con id: {}", id);

        if(grupoRepository.existsByCursoId(id))
            throw new EntidadRelacionadaException(
                    "No se puede eliminar el curso ya que tiene grupos asignados");

        cursoRepository.delete(curso);

        log.info("Curso {} eliminado correctamente", curso.getNombre());
    }

    private Curso obtenerCurso(Long id) {
        return ServiceUtils.obtenerEntidadOException(cursoRepository, id, Curso.class);
    }

    private void validarNombreUnico(CursoRequest request) {
        log.info("Validando curso único...");

        if(cursoRepository.existsByNombreIgnoreCase(request.nombre().trim()))
            throw new IllegalArgumentException("Ya existe un curso registrado con el nombre: " + request.nombre());
    }

    private void validarCambioUnico(CursoRequest request, Long id) {
        log.info("Validando cambio en curso único...");

        if(cursoRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id))
            throw new IllegalArgumentException("Ya existe un curso registrado con el nombre: " + request.nombre());
    }
}