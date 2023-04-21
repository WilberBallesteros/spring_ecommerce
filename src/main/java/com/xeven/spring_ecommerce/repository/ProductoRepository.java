
package com.xeven.spring_ecommerce.repository;

import com.xeven.spring_ecommerce.model.Producto;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepositoryImplementation<Producto, Integer>{
    
}
