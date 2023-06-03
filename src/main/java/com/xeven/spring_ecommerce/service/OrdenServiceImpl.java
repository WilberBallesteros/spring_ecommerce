
package com.xeven.spring_ecommerce.service;

import com.xeven.spring_ecommerce.model.Orden;
import com.xeven.spring_ecommerce.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImpl implements IOrdenService {
    
    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) { //guardar la orden en bd
       
        return ordenRepository.save(orden);
    }
    
}
