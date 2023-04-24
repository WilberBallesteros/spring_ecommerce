
package com.xeven.spring_ecommerce.controller;

import com.xeven.spring_ecommerce.model.Producto;
import com.xeven.spring_ecommerce.model.Usuario;
import com.xeven.spring_ecommerce.service.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @GetMapping("")
    public String show() {
        return "productos/show";
    }
    //metodo redirecciona a la vista crear productos
    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }
    
    @PostMapping("/save")
    public String save(Producto producto) {
        
        LOGGER.info("Este es el objeto producto {}", producto); //llamamos al metodo toString
        
        Usuario u = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(u);
        productoService.save(producto);
        return "redirect:/productos"; //carga la vista show
    }
    
}
