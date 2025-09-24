package com.rollerspeed.rollerspeed;

import com.rollerspeed.rollerspeed.models.Alumno;
import com.rollerspeed.rollerspeed.models.Rol;
import com.rollerspeed.rollerspeed.models.User;
import com.rollerspeed.rollerspeed.repositories.AlumnoRepository;
import com.rollerspeed.rollerspeed.repositories.RolRepository; // 1. Importar RolRepository

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // 2. Importar PasswordEncoder
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolRepository rolRepository;


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/mision")
    public String mision() {
        return "mision";
    }

    @GetMapping("/vision")
    public String vision() {
        return "vision";
    }

    @GetMapping("/valores")
    public String valores() {
        return "valores";
    }

    @GetMapping("/eventos")
    public String eventos() {
        return "eventos";
    }

    @GetMapping("/bienvenida")
    public String bienvenida() {
        return "bienvenida";
    }

    @GetMapping("/conocenos")
    public String conocenos() {
        return "conocenos";
    }
    


    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        Alumno alumno = new Alumno();
        alumno.setUser(new User()); 
        model.addAttribute("alumno", alumno);
        return "registro";
    }

    @PostMapping("/registro")
    public String processRegistration(@ModelAttribute Alumno alumno) {
        
        String contraseñaCifrada = passwordEncoder.encode(alumno.getUser().getPassword());
        alumno.getUser().setPassword(contraseñaCifrada);

        Rol rolAlumno = rolRepository.findByNombre("ROLE_ALUMNO");
        alumno.getUser().setRol(rolAlumno);
        
        alumnoRepository.save(alumno);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    
}
