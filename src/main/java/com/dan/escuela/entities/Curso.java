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

    @Builder.Default
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    public void validarDatos(String nombre, String descripcion, Integer creditos) {
        StringCustomUtils.validarTamanio(nombre, 1, 100, "El nombre es requerido y debe tener entre 1 y 100 caracteres");

        StringCustomUtils.validarTamanio(descripcion, 1, 200, "El apellido paterno es requerido y debe tener entre 1 y 50 caracteres");

        ValoresNumericosUtils.validarEnteroPositivo(creditos, "Los créditos deben ser mayores a 0");
    }

    public void actualizar(String nombre, String descripcion, Integer creditos) {
        validarDatos(nombre, descripcion, creditos);

        this.nombre = nombre.trim();
        this.descripcion = descripcion.trim();
        this.creditos = creditos;
    }
}
