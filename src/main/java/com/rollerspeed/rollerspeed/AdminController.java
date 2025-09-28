package com.rollerspeed.rollerspeed;

import com.rollerspeed.rollerspeed.models.Alumno;
import com.rollerspeed.rollerspeed.models.Clase;
import com.rollerspeed.rollerspeed.models.ClaseEstudiante;
import com.rollerspeed.rollerspeed.models.ClaseEstudianteId;
import com.rollerspeed.rollerspeed.models.Instructor;
import com.rollerspeed.rollerspeed.models.Pago;
import com.rollerspeed.rollerspeed.models.User;
import com.rollerspeed.rollerspeed.repositories.AlumnoRepository;
import com.rollerspeed.rollerspeed.repositories.ClaseEstudianteRepository;
import com.rollerspeed.rollerspeed.repositories.ClaseRepository;
import com.rollerspeed.rollerspeed.repositories.InstructorRepository;
import com.rollerspeed.rollerspeed.repositories.PagoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Tag(name = "Gestion de Administracion", description = "Endpoints para la administracion de instructores, clases y pagos")

public class AdminController {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ClaseEstudianteRepository claseEstudianteRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Operation(summary = " Obtener la lista de todos los instructores")
    @ApiResponse(responseCode = "200", description = "Lista de instructores mostrada exitosamente.")
    @GetMapping("/instructores")
    public String listarInstructores(Model model) {
        List<Instructor> listaInstructores = instructorRepository.findAll();
        model.addAttribute("instructores", listaInstructores);
        return "admin/instructores";
    }

    @Operation(summary = "Mostra el formulario para un nuevo instructor")
    @ApiResponse(responseCode = "200", description = "Formulario de creacion mostrado exitosamente")
    @GetMapping("/instructores/nuevo")
    public String mostrarFormularioDeNuevoInstructor(Model model) {
        Instructor instructor = new Instructor();
         instructor.setUser(new User());
        model.addAttribute("instructor", instructor);
        return "admin/formulario-instructor";
    }

    @Operation(summary = "Guarda el instructor (nuevo o existente)")
    @ApiResponse(responseCode = "302", description = "Instructor Guardado correctamente, redigire a la lista de instructores")
    @PostMapping("/instructores/guardar")
    public String guardarInstructor(@ModelAttribute Instructor instructor) {
        instructorRepository.save(instructor);
        return "redirect:/admin/instructores";
    }

    @Operation(summary = "Obtener un instructor por ID para editarlo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Formulario de edición mostrado con los datos del instructor."),
        @ApiResponse(responseCode = "404", description = "El instructor con el ID proporcionado no fue encontrado.")
    })
    @GetMapping("/instructores/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        Instructor instructor = instructorRepository.findById(id).get();
        model.addAttribute("instructor", instructor);
        return "admin/formulario-instructor";
    }

    @Operation(summary = "Eliminar instructor por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Instructor eliminado correctamente, redirigiendo a la lista."),
        @ApiResponse(responseCode = "404", description = "El instructor con el ID proporcionado no fue encontrado."),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor, posiblemente porque el instructor tiene clases asignadas.")
    })
    @GetMapping("/instructores/eliminar/{id}")
    public String eliminarInstructor(@PathVariable Long id) {
        instructorRepository.deleteById(id);
        return "redirect:/admin/instructores";
    }

    @Operation(summary = "Muestra la lista de clases")
    @ApiResponse(responseCode = "200", description = "Lista de clases mostrada exitosamente.")
    @GetMapping("/clases") 
    public String listarClases(Model model) {
        List<Clase> listarClases = claseRepository.findAll();
        model.addAttribute("clases", listarClases);
        return "admin/clases";
    }

    @Operation(summary = "Muestra el formulario para una nueva clase")
    @ApiResponse(responseCode = "200", description = "Formulario de creacion mostrado exitosamente")
    @GetMapping("/clases/nueva")
    public String mostrarFormularioDeNuevaClase(Model model) {
        List<Instructor> listaInstructores = instructorRepository.findAll();
        model.addAttribute("clase", new Clase());
        model.addAttribute("listaInstructores", listaInstructores);
        return "admin/formulario-clases";
    }

    @Operation(summary = "Guardar una clase (nueva o existente)")
    @ApiResponse(responseCode = "302", description = "Clase guardada exitosamente, redige a lista de clases")
    @PostMapping("/clases/guardar")
    public String guardarClase(@ModelAttribute Clase clase) {
        claseRepository.save(clase);
        return "redirect:/admin/clases";
    }
    @Operation(summary = "Obetner una clase por ID para editarla")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Formulario de edición mostrado con los datos de la clase."),
        @ApiResponse(responseCode = "404", description = "La clase con el ID proporcionado no fue encontrado.")
    })
    @GetMapping("/clases/editar/{id}")
    public String mostrarFormularioDeEditarClase(@PathVariable Long id, Model model) {
        Clase clase = claseRepository.findById(id).get();
        List<Instructor> listaInstructores = instructorRepository.findAll();
        model.addAttribute("clase", clase);
        model.addAttribute("listaInstructores", listaInstructores);
        return "admin/formulario-clases";
    }

    @Operation(summary = "Eliminar clase por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Clase eliminada correctamente, redirigiendo a la lista."),
        @ApiResponse(responseCode = "404", description = "La clase con el ID proporcionado no fue encontrado."),
    })
    @GetMapping("/clases/eliminar/{id}")
    public String eliminarClase(@PathVariable Long id) {
        claseRepository.deleteById(id);
        return "redirect:/admin/clases";
    }

    @Operation(summary = "Mostrar la pagina para gestionar inscripciones de una clase")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Página de gestión mostrada exitosamente."),
        @ApiResponse(responseCode = "404", description = "La clase con el ID proporcionado no fue encontrada.")
    })
    @GetMapping("/clases/{idClase}/inscripciones")
    public String gestionarInscripciones(@PathVariable Long idClase, Model model) {
        Clase clase = claseRepository.findById(idClase).get();
        List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
        List<ClaseEstudiante> inscripciones = claseEstudianteRepository.findByClaseIdClase(idClase);

        model.addAttribute("clase", clase);
        model.addAttribute("todosLosAlumnos", todosLosAlumnos);
        model.addAttribute("inscripciones", inscripciones);

        return "admin/gestion-inscripciones";
    }

    @Operation(summary = "Inscribir un alumno en una clase especifica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Alumno inscrito correctamente, redirigiendo a la página de gestión."),
        @ApiResponse(responseCode = "404", description = "La clase o el alumno no fueron encontrados."),
        @ApiResponse(responseCode = "500", description = "Error al intentar guardar la inscripción (ej: el alumno ya está inscrito).")
    })
    @PostMapping("/clases/inscribir")
    public String inscribirAlumnoEnClase(@RequestParam Long idClase, @RequestParam Long idAlumno) {
        Clase clase = claseRepository.findById(idClase).get();
        Alumno alumno = alumnoRepository.findById(idAlumno).get();
        ClaseEstudiante nuevaInscripcion = new ClaseEstudiante();
        ClaseEstudianteId id = new ClaseEstudianteId();
        id.setClaseId(idClase);
        id.setAlumnoId(idAlumno);
        nuevaInscripcion.setId(id);
        nuevaInscripcion.setClase(clase);
        nuevaInscripcion.setAlumno(alumno);
        nuevaInscripcion.setFechaInscripcion(java.time.LocalDate.now());
        claseEstudianteRepository.save(nuevaInscripcion);

        return "redirect:/admin/clases/" + idClase + "/inscripciones";
    }

    @Operation(summary = "Anular la inscripcion de un alumno en una clase")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Inscripción anulada correctamente, redirigiendo a la página de gestión."),
        @ApiResponse(responseCode = "404", description = "La inscripción a anular no fue encontrada (ID de clase o alumno incorrectos).")
    })
    @GetMapping("/clases/anular-inscripcion")
    public String anularInscripcion(@RequestParam Long idClase, @RequestParam Long idAlumno) {
        ClaseEstudianteId idParaBorrar = new ClaseEstudianteId();
        idParaBorrar.setClaseId(idClase);
        idParaBorrar.setAlumnoId(idAlumno);
        claseEstudianteRepository.deleteById(idParaBorrar);
        return "redirect:/admin/clases/" + idClase + "/inscripciones";
    }

    @Operation(summary = "Muestra la lista de todos los pagos")
    @ApiResponse(responseCode = "200", description = "Lista de pagos mostrada exitosamente.")
    @GetMapping("/pagos")
    public String listarPagos(Model model) {
        List<Pago> listaPagos = pagoRepository.findAll();
        model.addAttribute("pagos", listaPagos);
        return "admin/pagos";
    }

    @Operation(summary = "Mostrar el formulario para un nuevo pago")
    @ApiResponse(responseCode = "200", description = "Formulario de pago mostrado exitosamente")
    @GetMapping("/pagos/nuevo")
    public String mostrarFormularioDeNuevoPago(Model model) {
        List<Alumno> listaAlumnos = alumnoRepository.findAll();
        model.addAttribute("pago", new Pago());
        model.addAttribute("listaAlumnos", listaAlumnos);
        return "admin/formulario-pago";
    }

    @Operation(summary = "Guardar un pago (nuevo o existente)")
    @ApiResponse(responseCode = "200", description = "Pago guardado correctamente, redirige a la lista")
    @PostMapping("/pagos/guardar")
    public String guardarPago(@ModelAttribute Pago pago) {
        pagoRepository.save(pago);
        return "redirect:/admin/pagos";
    }

    @Operation(summary = "Obetener un pago por ID para editarlo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Formulario de edición mostrado con los datos del pago."),
        @ApiResponse(responseCode = "404", description = "El pago con el ID proporcionado no fue encontrado.")
    })
    @GetMapping("/pagos/editar/{id}")
    public String mostrarFormularioDeEditarPago(@PathVariable Long id, Model model) {
        Pago pago = pagoRepository.findById(id).get();
        List<Alumno> listaAlumnos = alumnoRepository.findAll();
        model.addAttribute("pago", pago);
        model.addAttribute("listaAlumnos", listaAlumnos);
        return "admin/formulario-pago";
    }

    @Operation(summary = "Eliminar pago por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Pago eliminado correctamente, redirigiendo a la lista."),
        @ApiResponse(responseCode = "404", description = "El pago con el ID proporcionado no fue encontrado."),
    })
    @GetMapping("/pagos/eliminar/{id}")
    public String eliminarPago(@PathVariable Long id) {
        pagoRepository.deleteById(id);
        return "redirect:/admin/pagos";
    }

    @Operation(summary = "Obtener la lista de todos los alumnos")
    @ApiResponse(responseCode = "200", description = "Lista de alumnos mostrada exitosamente.")
     @GetMapping("/alumnos")
    public String listarAlumnos(Model model) {
        List<Alumno> listaAlumnos = alumnoRepository.findAll();
        model.addAttribute("alumnos", listaAlumnos);
        return "admin/alumnos";
    }

    @Operation(summary = "Obtener un alumno por ID para editarlo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Formulario de edición mostrado con los datos del alumno."),
        @ApiResponse(responseCode = "404", description = "El alumno con el ID proporcionado no fue encontrado.")
    })
    @GetMapping("/alumnos/editar/{id}")
    public String mostrarFormularioDeEditarAlumno(@PathVariable Long id, Model model) {
        Alumno alumno = alumnoRepository.findById(id).get();
        model.addAttribute("alumno", alumno);
        return "admin/formulario-alumno";
    }

    @Operation(summary = "Guardar alumno (nuevo o existente)")
    @ApiResponse(responseCode = "302", description = "Alumno guardado correctamente, redirege a la lista")
    @PostMapping("/alumnos/guardar")
    public String guardarAlumno(@ModelAttribute Alumno alumno, @RequestParam(name = "newPassword", required = false) String newPassword) {
        Alumno alumnoExistente = alumnoRepository.findById(alumno.getIdAlumno()).get();
        alumnoExistente.setNombre(alumno.getNombre());
        alumnoExistente.setFechaNacimiento(alumno.getFechaNacimiento());
        alumnoExistente.setGenero(alumno.getGenero());
        alumnoExistente.setTelefono(alumno.getTelefono());
        alumnoExistente.getUser().setEmail(alumno.getUser().getEmail());

        if (newPassword != null && !newPassword.isEmpty()) {
            alumnoExistente.getUser().setPassword(passwordEncoder.encode(newPassword));  
        }

        alumnoRepository.save(alumnoExistente);
        return "redirect:/admin/alumnos";
    }

    @Operation(summary = "Eliminar alaumno por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Alumno eliminado correctamente, redirigiendo a la lista."),
        @ApiResponse(responseCode = "404", description = "El alumno con el ID proporcionado no fue encontrado."),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor, posiblemente porque el alumno esta inscrito en clases.")
    })
    @GetMapping("alumnos/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        alumnoRepository.deleteById(id);
        return "redirect:/admin/alumnos";
    }


    


    
}
