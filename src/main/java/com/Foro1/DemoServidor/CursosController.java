package com.Foro1.DemoServidor;

import java.util.List;

import com.Foro1.DemoServidor.Repository.CursosRepository;
import com.Foro1.DemoServidor.Repository.CursosRepository.Curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CursosController {
    @Autowired
    private CursosRepository cursosRep;

    public CursosController(CursosRepository cursosRep) {
        this.cursosRep = cursosRep;
    }

    @GetMapping("/cursos/")
    public String getCursos(@RequestParam String search, Model model) {
        List<Curso> cursos = cursosRep.getCoursesLikeName(search);
        if (cursos.isEmpty()) {
            model.addAttribute("errorMsg", "No se ha encontrado ningún resultado, prueba con \"curso\".");
            return "index.html";
        }
        model.addAttribute("cursos", cursos); System.out.println("Cursos encontrados: "+cursos);
        return "cursos";
    }

}
