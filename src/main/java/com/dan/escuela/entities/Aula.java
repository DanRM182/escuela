package com.dan.escuela.entities;

import com.dan.escuela.utils.StringCustomUtils;
import com.dan.escuela.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AULAS")
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AULA")
    private Long id;

    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;

    @Column(name = "CAPACIDAD", nullable = false)
    private Integer capacidad;

    @Builder.Default
    @OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    public void validarDatos(String nombre, Integer capacidad) {
        StringCustomUtils.validarTamanio(nombre, 1, 100, "El nombre es requerido y debe tener entre 1 y 100 caracteres");

        ValoresNumericosUtils.validarEnteroPositivo(capacidad, "La capacidad deben ser mayor a 0");
    }

    public void actualizar(String nombre, Integer capacidad) {
        validarDatos(nombre, capacidad);

        this.nombre = nombre.trim();
        this.capacidad = capacidad;
    }

}
