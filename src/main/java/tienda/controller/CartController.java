package tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.model.Product;
import tienda.services.CartService;
import tienda.services.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    // Agregar producto al carrito
    @PostMapping("/add")
    public String addProductToCart(@RequestParam Long productId) {
        Product product = productService.getProductById(productId);
        cartService.addProductToCart(product);  // Llamada al servicio para agregar el producto al carrito
        return "redirect:/cart";  // Redirige a la vista del carrito
    }

    // Eliminar producto del carrito
    @PostMapping("/remove")
    public String removeProductFromCart(@RequestParam Long productId) {
        Product product = productService.getProductById(productId);
        cartService.removeProductFromCart(product);  // Llamada al servicio para eliminar el producto
        return "redirect:/cart";  // Redirige a la vista del carrito
    }

    // Vaciar el carrito
    @PostMapping("/clear")
    public String clearCart() {
        cartService.clearCart();  // Llamada al servicio para vaciar el carrito
        return "redirect:/cart";  // Redirige a la vista del carrito
    }
}
