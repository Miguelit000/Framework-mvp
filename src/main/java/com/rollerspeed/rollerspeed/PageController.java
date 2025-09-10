package com.rollerspeed.rollerspeed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.rollerspeed.rollerspeed.models.User;
import com.rollerspeed.rollerspeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class PageController {

    private final RollerspeedApplication rollerspeedApplication;

    private final UserService userService;

    PageController(UserService userService, RollerspeedApplication rollerspeedApplication) {
        this.userService = userService;
        this.rollerspeedApplication = rollerspeedApplication;
    }

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

    @GetMapping("/servicios")
    public String servicios() {
        return "servicios";
    }

    @GetMapping("/eventos")
    public String eventos() {
        return "eventos";
    }

    // Metodo para mostrar el formulario de registro
    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registro";
    }

    // Metodo para procesar el formulario de registro
    @PostMapping("/registro")
    public String processRegistration(@ModelAttribute User user) {
        userService.savUser(user);
        return "redirect:/";
    }

    // Metodo para mostrar la pagina de inicio de sesion
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Metodo para procesar el incio de sesion (simulado)
    @PostMapping("/login")
    public String processLogin() {
        // En este paso, simplemente redirigimos.
        //Despues, agregamos la logica real de la validacion de usuario
        return "redirect:/";
    }
        
    
}