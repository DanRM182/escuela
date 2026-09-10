package com.dan.escuela.repositories;

import com.dan.escuela.entities.Horario;
import com.dan.escuela.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    boolean existsByGrupoId(Long grupoId);

    @Query("""
    SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END
    FROM Horario h
    JOIN h.grupo grupoExistente
    JOIN Grupo grupoNuevo ON grupoNuevo.id = :idGrupo
    WHERE grupoExistente.periodo = grupoNuevo.periodo
      AND h.diaSemana = :diaSemana
      AND h.horaInicio < :horaFin
      AND h.horaFin > :horaInicio
      AND (
          grupoExistente.id = grupoNuevo.id
          OR grupoExistente.aula.id = grupoNuevo.aula.id
      )
""")
    boolean validarTraslapesGrupoAulaPeriodo(
            @Param("idGrupo") Long idGrupo,
            @Param("diaSemana") DiaSemana diaSemana,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );

    @Query("""
    SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END
    FROM Horario h
    JOIN h.grupo grupoExistente
    JOIN Grupo grupoNuevo ON grupoNuevo.id = :idGrupo
    WHERE h.id <> :idHorario
      AND grupoExistente.periodo = grupoNuevo.periodo
      AND h.diaSemana = :diaSemana
      AND h.horaInicio < :horaFin
      AND h.horaFin > :horaInicio
      AND (
          grupoExistente.id = grupoNuevo.id
          OR grupoExistente.aula.id = grupoNuevo.aula.id
      )
""")
    boolean validarCambioTraslapesGrupoAulaPeriodo(
            @Param("idHorario") Long idHorario,
            @Param("idGrupo") Long idGrupo,
            @Param("diaSemana") DiaSemana diaSemana,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );
}
