package com.dan.escuela.dto.alumnos;

import com.dan.escuela.controllers.CrudController;
import com.dan.escuela.services.alumnos.AlumnoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "API Alumnos", description = "Métodos para gestión de alunos")
public class AlumnoController extends CrudController<AlumnoRequest, AlumnoResponse, AlumnoService> {
    public AlumnoController(AlumnoService service) {
        super(service);
    }
}
