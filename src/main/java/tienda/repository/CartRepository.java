package tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tienda.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // Métodos adicionales si los necesitas
}
