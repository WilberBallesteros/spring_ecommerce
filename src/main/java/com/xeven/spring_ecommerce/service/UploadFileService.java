
package com.xeven.spring_ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
    
    private String folder = "images//";
    
    public String saveImage(MultipartFile file) throws IOException { 
        //guardar la imagen
        //cuando el usuario a subido la imagen
        if(!file.isEmpty()) {
            byte [] bytes = file.getBytes();  //pasamos la imagen a bites
            Path path = Paths.get(folder+file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();  //retorna el nombre q tiene la imagen q he subido
        }
        //cuando el usuariono ha subido imagen
        return "default.jpg";
    }
    
    public void deleteImage(String nombre) {
        String ruta = "images//";
        File file = new File(ruta+nombre);
        file.delete();
    }
}
