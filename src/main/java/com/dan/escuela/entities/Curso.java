package com.dan.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CURSOS")
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long id;

    @Column(name = "NOMBRE", length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    @Column(name = "CREDITOS", nullable = false)
    private Integer creditos;
}
