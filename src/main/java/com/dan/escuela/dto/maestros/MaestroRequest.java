package com.dan.escuela.dto.maestros;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para registrar/actualizar un maestro")
public record MaestroRequest(
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
        @Schema(description = "Nombre del maestro", example = "Juan Gabriel")
        String nombre,

        @NotBlank(message = "El apellido paterno es requerido")
        @Size(min = 1, max = 50, message = "El apellido paterno debe tener entre 1 y 50 caracteres")
        @Schema(description = "Apellido paterno del maestro", example = "Gónzales")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es requerido")
        @Size(min = 1, max = 50, message = "El apellido materno debe tener entre 1 y 50 caracteres")
        @Schema(description = "Apellido materno del maestro", example = "Pérez")
        String apellidoMaterno,

        @NotBlank(message = "El email es requerido")
        @Size(min = 8, max = 100, message = "El email debe tener entre 8 y 100 caracteres")
        @Schema(description = "Email del maestro", example = "correo@correo.com")
        String email,

        @NotBlank(message = "El telefono es requerido")
        @Pattern(regexp = "^[0-9]{10}", message = "El teléfono debe tener 10 dígitos")
        @Schema(description = "Teléfono del maestro", example = "5512345678")
        String telefono
) { }
