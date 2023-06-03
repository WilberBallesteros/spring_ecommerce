
package com.xeven.spring_ecommerce.repository;

import com.xeven.spring_ecommerce.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer>{
    
}
