package com.dan.escuela.controllers;

import com.dan.escuela.dto.inscripciones.InscripcionRequest;
import com.dan.escuela.dto.inscripciones.InscripcionResponse;
import com.dan.escuela.services.inscripciones.InscripcionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripcion")
@Tag(name = "API Inscripciones", description = "Métodos para gestión de inscripciones")
public class InscripcionController extends CrudController<InscripcionRequest, InscripcionResponse, InscripcionService> {
    public InscripcionController(InscripcionService service) {
        super(service);
    }
}
