package tienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar un usuario por su nombre de usuario
    Optional<User> findByUsername(String username);

    // Buscar un usuario por su correo electrónico
    Optional<User> findByEmail(String email);
}
