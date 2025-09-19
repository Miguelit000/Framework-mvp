package com.rollerspeed.rollerspeed;

import com.rollerspeed.rollerspeed.models.User;
import com.rollerspeed.rollerspeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

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

    // Metodo para procesar el incio de sesion
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsernameAndPassword(username, password);

        if (user != null) {
            // Si el usuario existe, redirige a una pagina de bienvenida
            return "redirect:/bienvenida";
        } else {
            // Si el usuario no existe, regresa a la pagina de login con error
            return "redirect:/login?error=true";
        }
    }
}