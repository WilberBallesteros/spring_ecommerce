
package com.xeven.spring_ecommerce.service;

import com.xeven.spring_ecommerce.model.Orden;
import com.xeven.spring_ecommerce.repository.IOrdenRepository;
import java.util.ArrayList;
import java.util.List;
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

    //
    @Override
    public List<Orden> findAll() {
       return ordenRepository.findAll();//mirar ciual es el ultimo numero secuencial de la orden
    }
    
    //secuencial string numeroOrden
    //orden 0000010 
    public String generarNumeroOrden() {
        
        int numero = 0;
        String numeroConcatenado = "";
        
        List<Orden> ordenes = findAll();//obtener todas las ordenes
        List<Integer> numeros = new ArrayList<Integer>();
        
        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
        
        if(ordenes.isEmpty()) {
            numero = 1;
        } else {
            numero = numeros.stream().max(Integer::compare).get(); //el numero mayor de toda la lista
            numero++;
        }
        
        //pasarlo a cadena
        if(numero < 10) { //0000000001, 0000000002, 0000000003...
            numeroConcatenado = "000000000" + String.valueOf(numero);
        } else if( numero < 100 ) {
            numeroConcatenado = "00000000" + String.valueOf(numero);
        } else if( numero < 1000 ) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        }
        else if( numero < 10000 ) {
            numeroConcatenado = "000000" + String.valueOf(numero);
        }
        
        return numeroConcatenado;
    }
    
}
