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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")

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

    @GetMapping("/instructores")
    public String listarInstructores(Model model) {
        List<Instructor> listaInstructores = instructorRepository.findAll();
        model.addAttribute("instructores", listaInstructores);
        return "admin/instructores";
    }

    @GetMapping("/instructores/nuevo")
    public String mostrarFormularioDeNuevoInstructor(Model model) {
        Instructor instructor = new Instructor();
         instructor.setUser(new User());
        model.addAttribute("instructor", instructor);
        return "admin/formulario-instructor";
    }

    @PostMapping("/instructores/guardar")
    public String guardarInstructor(@ModelAttribute Instructor instructor) {
        instructorRepository.save(instructor);
        return "redirect:/admin/instructores";
    }

    @GetMapping("/instructores/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        Instructor instructor = instructorRepository.findById(id).get();
        model.addAttribute("instructor", instructor);
        return "admin/formulario-instructor";
    }

    @GetMapping("/instructores/eliminar/{id}")
    public String eliminarInstructor(@PathVariable Long id) {
        instructorRepository.deleteById(id);
        return "redirect:/admin/instructores";
    }

    @GetMapping("/clases") 
    public String listarClases(Model model) {
        List<Clase> listarClases = claseRepository.findAll();
        model.addAttribute("clases", listarClases);
        return "admin/clases";
    }

    @GetMapping("/clases/nueva")
    public String mostrarFormularioDeNuevaClase(Model model) {
        List<Instructor> listaInstructores = instructorRepository.findAll();
        model.addAttribute("clase", new Clase());
        model.addAttribute("listaInstructores", listaInstructores);
        return "admin/formulario-clases";
    }

    @PostMapping("/clases/guardar")
    public String guardarClase(@ModelAttribute Clase clase) {
        claseRepository.save(clase);
        return "redirect:/admin/clases";
    }

    @GetMapping("/clases/editar/{id}")
    public String mostrarFormularioDeEditarClase(@PathVariable Long id, Model model) {
        Clase clase = claseRepository.findById(id).get();
        List<Instructor> listaInstructores = instructorRepository.findAll();
        model.addAttribute("clase", clase);
        model.addAttribute("listaInstructores", listaInstructores);
        return "admin/formulario-clases";
    }

    @GetMapping("/clases/eliminar/{id}")
    public String eliminarClase(@PathVariable Long id) {
        claseRepository.deleteById(id);
        return "redirect:/admin/clases";
    }

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

    @GetMapping("/clases/anular-inscripcion")
    public String anularInscripcion(@RequestParam Long idClase, @RequestParam Long idAlumno) {
        ClaseEstudianteId idParaBorrar = new ClaseEstudianteId();
        idParaBorrar.setClaseId(idClase);
        idParaBorrar.setAlumnoId(idAlumno);
        claseEstudianteRepository.deleteById(idParaBorrar);
        return "redirect:/admin/clases/" + idClase + "/inscripciones";
    }
    
    @GetMapping("/pagos")
    public String listarPagos(Model model) {
        List<Pago> listaPagos = pagoRepository.findAll();
        model.addAttribute("pagos", listaPagos);
        return "admin/pagos";
    }

    @GetMapping("/pagos/nuevo")
    public String mostrarFormularioDeNuevoPago(Model model) {
        List<Alumno> listaAlumnos = alumnoRepository.findAll();
        model.addAttribute("pago", new Pago());
        model.addAttribute("listaAlumnos", listaAlumnos);
        return "admin/formulario-pago";
    }

    @PostMapping("/pagos/guardar")
    public String guardarPago(@ModelAttribute Pago pago) {
        pagoRepository.save(pago);
        return "redirect:/admin/pagos";
    }

    @GetMapping("/pagos/editar/{id}")
    public String mostrarFormularioDeEditarPago(@PathVariable Long id, Model model) {
        Pago pago = pagoRepository.findById(id).get();
        List<Alumno> listaAlumnos = alumnoRepository.findAll();
        model.addAttribute("pago", pago);
        model.addAttribute("listaAlumnos", listaAlumnos);
        return "admin/formulario-pago";
    }

    @GetMapping("/pagos/eliminar/{id}")
    public String eliminarPago(@PathVariable Long id) {
        pagoRepository.deleteById(id);
        return "redirect:/admin/pagos";
    }


    


    
}
