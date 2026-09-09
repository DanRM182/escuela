package com.dan.escuela.controllers;

import com.dan.escuela.dto.aulas.AulaRequest;
import com.dan.escuela.dto.aulas.AulaResponse;
import com.dan.escuela.services.aulas.AulaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aulas")
@Tag(name = "API Aulas", description = "Métodos para gestión de aulas")
public class AulaController extends CrudController<AulaRequest, AulaResponse, AulaService> {
    public AulaController(AulaService service) {
        super(service);
    }
}
