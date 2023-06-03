
package com.xeven.spring_ecommerce.service;

import com.xeven.spring_ecommerce.model.DetalleOrden;
import com.xeven.spring_ecommerce.repository.IDetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {
    
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        
        return detalleOrdenRepository.save(detalleOrden);
    }
    
}
