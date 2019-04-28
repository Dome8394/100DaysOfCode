package springdata.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springdata.products.entities.Product;
import springdata.products.services.ProductService;

import java.util.List;

/**
 * Controller class responsible for handling requests regards products.
 * @author dominik
 * @date 28.04.19
 */
@RestController
@RequestMapping(value = "api/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAll();
    }

}
