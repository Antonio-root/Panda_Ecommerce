package tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.exception.ProductNotFoundException;
import tienda.model.Product;
import tienda.repository.ProductRepository;  // Agregar la importación de List

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Método para obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Método para obtener un producto por ID
    public Product getProductById(Long id) {
    return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con el ID: " + id));
}


    // Método para buscar productos por nombre
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }
}
