
package com.xeven.spring_ecommerce.controller;

import com.xeven.spring_ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private ProductoService productoService; //obtener los productos
    
    @GetMapping("")
    public String home(Model model) { //Model para q pueda llevar la info de productos a la vista
        
        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }
}
