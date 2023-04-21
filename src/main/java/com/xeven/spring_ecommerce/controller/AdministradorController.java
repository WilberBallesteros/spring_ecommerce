
package com.xeven.spring_ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @GetMapping("") //nos redireccione a la home
    public String home() {
        return "administrador/home";
    }
}
