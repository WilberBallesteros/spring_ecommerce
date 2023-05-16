
package com.xeven.spring_ecommerce.controller;

import com.xeven.spring_ecommerce.model.Producto;
import com.xeven.spring_ecommerce.service.ProductoService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    private final Logger log =LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private ProductoService productoService; //obtener los productos
    
    @GetMapping("")
    public String home(Model model) { //Model para q pueda llevar la info de productos a la vista
        
        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }
    
    //cuando le demos click al boton ver producto nos lleve a la vista ver producto
    @GetMapping("productohome/{id}")
    public String ProductoHome(@PathVariable Integer id, Model model) { //@PathVariable el id lo toma de la url
        
        log.info("Id producto enviado como parametro {}", id);
        
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        
        //lo enviamos a la vista
        model.addAttribute("producto", producto);
        
        return "usuario/productohome";
    }
}
