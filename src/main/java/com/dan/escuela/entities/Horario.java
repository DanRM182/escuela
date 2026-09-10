package com.dan.escuela.entities;

import com.dan.escuela.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "HORARIOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIA", nullable = false)
    private DiaSemana diaSemana;

    @Column(name = "HORA_INICIO", length = 5, nullable = false)
    private String horaInicio;

    @Column(name = "HORA_FIN", length = 5, nullable = false)
    private String horaFin;

    public void asignarGrupoDiaSemana(Grupo grupo, DiaSemana diaSemana) {
        this.grupo = grupo;
        this.diaSemana = diaSemana;
    }

    public String horarioConcatenado(String dia, String horaInicio, String horaFin){
        return String.join(" ", dia,horaInicio, "-", horaFin);
    }

    public void actualizar(Grupo grupo, DiaSemana diaSemana, String horaInicio, String horaFin) {
        this.grupo = grupo;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
