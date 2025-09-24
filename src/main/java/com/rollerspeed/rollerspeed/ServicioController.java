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

        model.addAttribute("servicios", servicioService.findActiveServices());
        return "servicios";
    }

}