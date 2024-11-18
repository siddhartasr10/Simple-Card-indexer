package com.Foro1.DemoServidor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController {
    // @Autowired no es necesario usando views con un templating engine
    // private ResourceLoader res;

	@GetMapping("/")
	public String rootView() {
        return "index.html";
    }

}
