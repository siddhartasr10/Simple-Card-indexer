package com.Foro1.DemoServidor;

import java.util.Objects;

import com.Foro1.DemoServidor.Repository.CursosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImgController {
    @Autowired
    private CursosRepository cursosRep;

    public ImgController(CursosRepository cursosRep) {
        this.cursosRep = cursosRep;
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<byte[]> getImg(@PathVariable Integer id) {
        byte[] img = cursosRep.getImgById(id);
        var headers = new HttpHeaders(); headers.set("Content-Type", "image/webp");
        if (!Objects.isNull(img)) return new ResponseEntity<>(img, headers, HttpStatus.OK);
        return ResponseEntity.notFound().build();
    }
}
