package tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.model.User;
import tienda.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Mostrar formulario de inicio de sesión
    @GetMapping("/login")
    public String login() {
        return "login";  // Vista de inicio de sesión
    }

    // Procesar inicio de sesión
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        User user = userService.authenticate(username, password);  // Llamamos al método de autenticación

        if (user != null) {
            return "redirect:/";  // Redirigir a la página principal si el login es exitoso
        } else {
            // Enviar un mensaje de error al usuario (si lo deseas)
            return "login?error=true";  // Redirigir a login con un parámetro de error
        }
    }

    // Mostrar formulario de registro
    @GetMapping("/register")
    public String register() {
        return "register";  // Vista de registro
    }

    // Procesar registro de usuario
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        // Llamamos al método registerUser de UserService
        userService.registerUser(username, email, password);
        return "redirect:/user/login";  // Redirigir a la vista de login después de registrarse
    }

    // Mostrar perfil de usuario (si lo deseas)
    @GetMapping("/profile")
    public String profile(Model model) {
        User user = userService.getCurrentUser();  // Obtener el usuario autenticado

        // Verificamos si se obtuvo un usuario
        if (user != null) {
            model.addAttribute("user", user);
            return "profile";  // Vista de perfil
        } else {
            return "redirect:/user/login";  // Redirigir al login si no hay usuario autenticado
        }
    }
}
