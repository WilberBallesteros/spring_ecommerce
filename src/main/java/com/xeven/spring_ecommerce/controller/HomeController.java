
package com.xeven.spring_ecommerce.controller;

import com.xeven.spring_ecommerce.model.DetalleOrden;
import com.xeven.spring_ecommerce.model.Orden;
import com.xeven.spring_ecommerce.model.Producto;
import com.xeven.spring_ecommerce.model.Usuario;
import com.xeven.spring_ecommerce.service.IDetalleOrdenService;
import com.xeven.spring_ecommerce.service.IOrdenService;
import com.xeven.spring_ecommerce.service.IUsuaruiService;
import com.xeven.spring_ecommerce.service.ProductoService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
    
    private final Logger log =LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private ProductoService productoService; //obtener los productos
    
    @Autowired
    private IUsuaruiService usuarioService;
    
    @Autowired
    private IOrdenService ordenService;
    
    @Autowired
    private IDetalleOrdenService detalleOrdenservice;
    
    //para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
    
    //datos de la orden
    Orden orden = new Orden();
    
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
    
    //añade los productos al carrito de compras
    @PostMapping("/cart") //esta url termina en cart
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;
        
        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {} ", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        
        producto = optionalProducto.get();
        
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad); //multiplicado por la cantidad q puso el usuario
        detalleOrden.setProducto(producto);//poner la llave foranea para detalle orden q es producto
        
        
        //validar q el producto no se agregue mas de una vez
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);//el id ya se encuentra en la lista de detalles q es el carrito
        
        if (!ingresado) {
            detalles.add(detalleOrden);
        }
        
        //sumar el total de lo q añada el usuario al carrito
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        
        //suma de toda la lista del carrito
        orden.setTotal(sumaTotal);
        
        //pasar estas variables hacia la vista
        model.addAttribute("cart", detalles); //detalles todos los productos q vaya añadiendo
        model.addAttribute("orden", orden);
        
        return "usuario/carrito";
    }
    
    //quitar un producto del carrito
    
     @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model) {
        
        //lista nueva de productos
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();
        
        for(DetalleOrden detalleOrden: detalles) {
            if(detalleOrden.getProducto().getId() != id) { //un id q este en detalles no lo añade a la nueva lista
                ordenesNueva.add(detalleOrden);
            }
        }
        
        //poner la nueva lista con los productos restantes
        detalles = ordenesNueva;
        
        //recalculamos lo q esta en la orden
        double sumaTotal = 0;
        
         //sumar el total de lo q añada el usuario al carrito
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        
        //suma de toda la lista del carrito
        orden.setTotal(sumaTotal);
        
        //pasar estas variables hacia la vista
        model.addAttribute("cart", detalles); //detalles todos los productos q vaya añadiendo
        model.addAttribute("orden", orden);
        
        return "usuario/carrito";
    }
    
    //mostrar carrito cuando le damos a ver carrito jaja
    @GetMapping("/getCart")
    public String getCart(Model model) {
        
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        return "/usuario/carrito";
    }
    
    //metodo para mostrar toda la orden con sus detalles
    @GetMapping("/order")
    public String order(Model model) {
        
        Usuario usuario = usuarioService.findById(1).get(); //despues con iniciar sesion
        
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        
        return "usuario/resumenorden";
    }
    
    //guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder() {
        
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());
        
        //usuario q hace esa orden
         Usuario usuario = usuarioService.findById(1).get();
         
         orden.setUsuario(usuario);
         ordenService.save(orden); //guardamos los datos de la orden con su id
         
         //guardar detalles
         for (DetalleOrden dt: detalles) {
            dt.setOrden(orden);
            detalleOrdenservice.save(dt);
        }
         
         ///limpiar lista y orden
         orden = new Orden();
         detalles.clear();
        
        return "redirect:/";
    }
    
}
