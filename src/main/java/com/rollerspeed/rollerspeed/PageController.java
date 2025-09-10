package com.rollerspeed.rollerspeed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rollerspeed.rollerspeed.models.User;
import com.rollerspeed.rollerspeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class PageController {

    private final UserService userService;

    PageController(UserService userService) {
        this.userService = userService;
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
        
    
}