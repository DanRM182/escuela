package com.dan.escuela.repositories;

import com.dan.escuela.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    boolean existsByGrupoId(Long grupoId);
}
