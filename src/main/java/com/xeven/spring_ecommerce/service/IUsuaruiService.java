
package com.xeven.spring_ecommerce.service;

import com.xeven.spring_ecommerce.model.Usuario;
import java.util.Optional;

public interface IUsuaruiService {
    
    Optional<Usuario> findById(Integer Id);
}
