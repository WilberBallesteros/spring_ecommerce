
package com.xeven.spring_ecommerce.controller;

import com.xeven.spring_ecommerce.model.Producto;
import com.xeven.spring_ecommerce.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("") //nos redireccione a la home
    public String home(Model model) {
        
        //obtiene los productos
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        
        return "administrador/home";
    }
}
