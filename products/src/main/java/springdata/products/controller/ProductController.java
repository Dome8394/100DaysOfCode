package springdata.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springdata.products.entities.Product;
import springdata.products.services.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling requests regards products.
 *
 * @author dominik
 * @date 28.04.19
 */
@RestController
@RequestMapping(value = "api/")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Endpoint to retrieve all available products.
     *
     * @return List
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAll();
    }

    /**
     * Endpoint to retrieve a single product by its ID.
     *
     * @param id
     * @return Product entity
     */
    @RequestMapping(value = "/product/{id}")
    public Optional<Product> getSingleProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    /**
     * Endpoint to add a product to the sortiment.
     *
     * @param product
     * @return String
     */
    @RequestMapping(value = "/product/new", method = RequestMethod.POST,
            consumes = {"application/json", "application/x-www-form-urlencoded"},
            produces = {"application/json", "application/x-www-form-urlencoded"})
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return product.toString();
    }
}
