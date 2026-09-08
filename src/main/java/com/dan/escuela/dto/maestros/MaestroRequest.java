package com.dan.escuela.dto.maestros;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Información de un maestro")
public record MaestroRequest(
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        @Schema(description = "Nombre del maestro", example = "Juan Gabriel")
        String nombre,

        @NotBlank(message = "El apellido paterno es requerido")
        @Size(min = 2, max = 50, message = "El apellido paterno debe tener entre 2 y 50 caracteres")
        @Schema(description = "Apellido paterno del maestro", example = "Gónzales")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es requerido")
        @Size(min = 2, max = 50, message = "El apellido materno debe tener entre 2 y 50 caracteres")
        @Schema(description = "Apellido materno del maestro", example = "Pérez")
        String apellidoMaterno,

        @NotBlank(message = "El email es requerido")
        @Size(min = 5, max = 100, message = "El email debe tener entre 5 y 100 caracteres")
        @Schema(description = "Email del maestro", example = "correo@correo.com")
        String email,

        @NotBlank(message = "El telefono es requerido")
        @Size(min = 5, max = 10, message = "El telefono debe tener entre 5 y 10 caracteres")
        @Schema(description = "Numero del maestro", example = "5512345678")
        String telefono
) {
}
