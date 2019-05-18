package springdata.products.controller;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/api")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ProductService productService;

    /**
     * Endpoint to retrieve all available products.
     *
     * @return List
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {

        logger.info("Endpoint /products hit!");

        return productService.getAll();
    }

    /**
     * Endpoint to retrieve a single product by its ID.
     *
     * @param id
     * @return Product entity
     */
    @RequestMapping(value = "/products/find/{id}")
    public Optional<Product> getSingleProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    /**
     * Endpoint to add a product to the sortiment.
     *
     * @param productDetails
     * @return String
     */
    @RequestMapping(value = "/product/add", method = RequestMethod.POST,
            consumes = {"application/json", "application/x-www-form-urlencoded"},
            produces = {"application/json", "application/x-www-form-urlencoded"})
    public String addProduct(@RequestBody Product productDetails) {

        // logging info if endpoint is even called by dispatcher
        logger.info("Endpoint '/product/new' hit!");

        productService.addProduct(productDetails);
        return productDetails.toString();
    }
}
