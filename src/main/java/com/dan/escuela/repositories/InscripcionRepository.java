package com.dan.escuela.repositories;

import com.dan.escuela.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByAlumnoIdAndGrupoId(Long alumnoId, Long grupoId);

    boolean existsByAlumnoIdAndGrupoIdAndIdNot(Long alumnoId, Long grupoId, Long id);

    boolean existsByAlumnoId(Long idAlumno);

    boolean existsByGrupoId(Long grupoId);
}
