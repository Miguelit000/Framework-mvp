package com.rollerspeed.rollerspeed;

import com.rollerspeed.rollerspeed.models.Instructor;
import com.rollerspeed.rollerspeed.models.User;
import com.rollerspeed.rollerspeed.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping("/instructores")
    public String listarInstructores(Model model) {
        List<Instructor> listarInstructores = instructorRepository.findAll();
        model.addAttribute("instructores", listarInstructores);
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
        Instructor  instructor = instructorRepository.findById(id).get();
        model.addAttribute("instructor", instructor);
        return "admin/formulario-instructor";
    }
    
    @GetMapping("/instructores/eliminar/{id}")
    public String eliminarInstructor(@PathVariable Long id) {
        instructorRepository.deleteById(id);
        return "redirect:/admin/instructores";
    }
}
