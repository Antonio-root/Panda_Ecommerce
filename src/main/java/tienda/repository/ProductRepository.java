package tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String keyword);  // Método para búsqueda por nombre
}
