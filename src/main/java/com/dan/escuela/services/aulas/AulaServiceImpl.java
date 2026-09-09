package com.dan.escuela.services.aulas;

import com.dan.escuela.dto.aulas.AulaRequest;
import com.dan.escuela.dto.aulas.AulaResponse;
import com.dan.escuela.dto.cursos.CursoRequest;
import com.dan.escuela.entities.Aula;
import com.dan.escuela.entities.Curso;
import com.dan.escuela.exceptions.EntidadRelacionadaException;
import com.dan.escuela.mappers.AulaMapper;
import com.dan.escuela.repositories.AulaRepository;
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
public class AulaServiceImpl implements AulaService {
    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;
    private final GrupoRepository grupoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AulaResponse> listar() {
        log.info("Listando todas las aulas");

        return aulaRepository.findAll().stream()
                .map(aulaMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AulaResponse obtenerPorId(Long id) {
        log.info("Buscando aula por ID");

        return aulaMapper.entidadAResponse(obtenerAula(id));
    }

    @Override
    public AulaResponse registrar(AulaRequest request) {
        log.info("Registrando nueva aula...");

        validarNombreUnico(request);

        Aula aula = aulaMapper.requestAEntidad(request);

        aulaRepository.save(aula);

        log.info("Nuevo curso {} creado", aula.getNombre());

        return aulaMapper.entidadAResponse(aula);
    }

    @Override
    public AulaResponse actualizar(AulaRequest request, Long id) {
        Aula aula = obtenerAula(id);

        log.info("Actualizando aula con id: {}", id);

        validarCambioUnico(request, id);

        aula.actualizar(
                request.nombre(),
                request.capacidad());

        log.info("Aula {} actualizada correctamente", aula.getNombre());

        return aulaMapper.entidadAResponse(aula);
    }

    @Override
    public void eliminar(Long id) {
        Aula aula = obtenerAula(id);

        log.info("Eliminando aula con id: {}", id);

        if(grupoRepository.existsByAulaId(id))
            throw new EntidadRelacionadaException(
                    "No se puede eliminar el aula ya que tiene grupos asignados");

        aulaRepository.delete(aula);

        log.info("Aula {} eliminada correctamente", aula.getNombre());
    }

    private Aula obtenerAula(Long id) {
        return ServiceUtils.obtenerEntidadOException(aulaRepository, id, Aula.class);
    }

    private void validarNombreUnico(AulaRequest request) {
        log.info("Validando curso único...");

        if(aulaRepository.existsByNombreIgnoreCase(request.nombre().trim()))
            throw new IllegalArgumentException("Ya existe un aula registrada con el nombre: " + request.nombre());
    }

    private void validarCambioUnico(AulaRequest request, Long id) {
        log.info("Validando cambio en curso único...");

        if(aulaRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id))
            throw new IllegalArgumentException("Ya existe un curso registrado con el nombre: " + request.nombre());
    }
}
