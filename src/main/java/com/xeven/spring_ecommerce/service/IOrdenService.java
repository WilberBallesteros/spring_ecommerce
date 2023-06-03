
package com.xeven.spring_ecommerce.service;

import com.xeven.spring_ecommerce.model.Orden;
import java.util.List;

public interface IOrdenService {
    
    List<Orden> findAll();
    
    Orden save(Orden orden);
    
    String generarNumeroOrden();
}
