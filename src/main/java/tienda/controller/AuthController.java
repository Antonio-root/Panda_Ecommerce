package tienda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.services.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Página de inicio de sesión
    @GetMapping("/login")
    public String login() {
        return "login";  // Muestra la página de login
    }

    // Página de registro
    @GetMapping("/register")
    public String register() {
        return "register";  // Muestra la página de registro
    }

    // Procesar el registro de un nuevo usuario
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        try {
            // Registrar usuario en la base de datos
            userService.registerUser(username, email, password);
            return "redirect:/login";  // Redirige a la página de login después de registrar
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());  // Muestra un error si el registro falla
            return "register";  // Vuelve a mostrar el formulario de registro
        }
    }

    // Procesar el inicio de sesión (se maneja automáticamente por Spring Security)
    // No es necesario escribir un método explícito para POST en /login
}

