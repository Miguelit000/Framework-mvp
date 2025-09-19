package com.rollerspeed.rollerspeed; // Asegúrate que el paquete sea el correcto

import com.rollerspeed.rollerspeed.services.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping("/servicios")
    public String listarServicios(Model model) {
        // Añade la lista de servicios activos al modelo para que la vista pueda usarla
        model.addAttribute("servicios", servicioService.findActiveServices());
        return "servicios"; // Llama al archivo servicios.html
    }

    // Aquí irían otros métodos para ver detalles de un servicio, crear uno nuevo, etc.
    // Por ejemplo: @GetMapping("/servicios/{id}")
}