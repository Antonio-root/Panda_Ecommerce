package tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.model.Product;
import tienda.services.ProductService;

@Controller
@RequestMapping("/resources/templates/products.html")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Mostrar lista de productos
    @GetMapping
    public String getProductList(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";  // Vista de lista de productos
    }

    // Mostrar detalles de un producto
    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";  // Vista de detalles del producto
    }

    // Buscar productos por nombre
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        return "product-list";  // Vista de resultados de búsqueda
    }
}
