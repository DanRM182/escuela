package com.dan.escuela.repositories;

import com.dan.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    boolean existsByCursoIdAndMaestroIdAndAulaIdAndPeriodo(Long cursoId, Long maestroId, Long aulaId, String periodo);

    boolean existsByCursoIdAndMaestroIdAndAulaIdAndPeriodoAndIdNot(Long cursoId, Long maestroId, Long aulaId, String periodo, Long id);

    boolean existsByMaestroId(Long idMaestro);

    boolean existsByCursoId(Long idCurso);

    boolean existsByAulaId(Long idAula);
}
