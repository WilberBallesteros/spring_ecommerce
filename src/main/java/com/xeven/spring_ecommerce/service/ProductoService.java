
package com.xeven.spring_ecommerce.service;

import com.xeven.spring_ecommerce.model.Producto;
import java.util.Optional;

public interface ProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);//validar si el objeto q tenemos en la base de datos existe o no
    public void update(Producto producto);
    public void delete(Integer id);
}
