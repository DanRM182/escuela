package com.dan.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAESTROS")
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAESTRO")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", length = 50, nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50, nullable = false)
    private String apellidoMaterno;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "Telefono", length = 10, nullable = false)
    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy = "maestro", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();
}
