package tienda.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.model.Cart;
import tienda.model.Product;
import tienda.repository.CartRepository;
import tienda.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;  // Repositorio de carrito

    @Autowired
    private ProductRepository productRepository;  // Repositorio de productos

    // Crear un nuevo carrito para un usuario (puedes mejorar esto añadiendo usuarios más adelante)
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);  // Guardamos el carrito en la base de datos
    }

    // Obtener el carrito actual. Asumimos que el carrito existe en la base de datos y tiene productos.
    public Cart getCart() {
        // Aquí puedes agregar lógica para asociar un carrito con un usuario si implementas autenticación
        List<Cart> carts = cartRepository.findAll();  // Obtiene todos los carritos
        if (!carts.isEmpty()) {
            return carts.get(0);  // Retorna el primer carrito disponible (esto es solo para ejemplo)
        }
        return null;  // Si no hay carritos, retorna null
    }

    // Agregar un producto al carrito
    public void addProductToCart(Product product) {
        Cart cart = getCart();  // Obtener el carrito actual

        if (cart == null) {
            cart = createCart();  // Si no existe un carrito, creamos uno nuevo
        }

        // Agregar el producto al carrito
        cart.getProducts().add(product);  // Agrega el producto a la lista de productos en el carrito
        cartRepository.save(cart);  // Guardamos el carrito actualizado en la base de datos
    }

    // Eliminar un producto del carrito
    public void removeProductFromCart(Product product) {
        Cart cart = getCart();  // Obtener el carrito actual

        if (cart != null) {
            cart.getProducts().remove(product);  // Eliminar el producto del carrito
            cartRepository.save(cart);  // Guardar el carrito actualizado
        }
    }

    // Vaciar el carrito
    public void clearCart() {
        Cart cart = getCart();  // Obtener el carrito actual

        if (cart != null) {
            cart.getProducts().clear();  // Vaciar la lista de productos
            cartRepository.save(cart);  // Guardar el carrito vacío
        }
    }
}

