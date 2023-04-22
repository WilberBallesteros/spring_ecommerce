
package com.xeven.spring_ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @GetMapping("")
    public String show() {
        return "productos/show";
    }
    //metodo redirecciona a la vista crear productos
    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }
}
