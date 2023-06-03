
package com.xeven.spring_ecommerce.service;

import com.xeven.spring_ecommerce.model.Usuario;
import com.xeven.spring_ecommerce.repository.IUsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuaruiService{
    
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findById(Integer Id) {
        return usuarioRepository.findById(Id);
    }

   
    
}
