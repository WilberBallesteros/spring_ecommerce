
package com.xeven.spring_ecommerce.repository;

import com.xeven.spring_ecommerce.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer>{
    
}
