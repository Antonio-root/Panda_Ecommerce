package tienda.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tienda.model.User;
import tienda.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;  // Repositorio de usuarios

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // Para cifrar contraseñas

    // Registrar un nuevo usuario
    @Transactional
    public User registerUser(String username, String email, String password) {
        // Verifica si el usuario o correo ya existen
        Optional<User> existingUserByUsername = userRepository.findByUsername(username);
        if (existingUserByUsername.isPresent()) {
            throw new IllegalStateException("El nombre de usuario ya está en uso.");
        }

        Optional<User> existingUserByEmail = userRepository.findByEmail(email);
        if (existingUserByEmail.isPresent()) {
            throw new IllegalStateException("El correo electrónico ya está en uso.");
        }

        // Cifra la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(password);

        // Crear el nuevo usuario con roles predeterminados
        User user = new User(username, email, encodedPassword, List.of("ROLE_USER"));

        return userRepository.save(user);
    }

    // Autenticación de usuario: Verificar nombre de usuario y contraseña
    public User authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            return null; // Usuario no encontrado
        }

        User user = userOptional.get();

        // Verifica que la contraseña coincida
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null; // Contraseña incorrecta
        }

        return user; // Autenticación exitosa
    }

    // Buscar un usuario por su nombre de usuario
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Buscar un usuario por su correo electrónico
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Cargar el usuario para Spring Security (UserDetails)
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("Usuario no encontrado");
        }

        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
            user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
    }
    // Obtener el usuario actualmente autenticado
    public User getCurrentUser() {
        // Obtiene el principal (usuario) del contexto de seguridad
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Si el principal es una instancia de UserDetails, obtenemos el nombre de usuario
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            // Buscar el usuario en la base de datos por su nombre de usuario
            return userRepository.findByUsername(username).orElse(null);  // Devuelve el usuario si lo encuentra
        }
        
        return null;  // Si no está autenticado, retorna null
    }
}
