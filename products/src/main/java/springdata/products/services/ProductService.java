package springdata.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import springdata.products.entities.Product;
import springdata.products.interfaces.IProduct;
import springdata.products.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation of the IProduct interface.
 *
 * @author Dominik
 * @date 01.05.2019
 */
@Component
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository repository;

    /**
     * Queries a list of products from the database.
     *
     * @return List
     */
    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    /**
     * Queries a single product entity from the database.
     *
     * @param id
     * @return Product entity
     */
    public Optional<Product> getProduct(int id) {
        return repository.findById(id);
    }

    /**
     * Add a product to the sortiment.
     *
     * @param product
     * @return String
     */
    @Override
    public String addProduct(Product product) {

        if ((!repository.exists(Example.of(product)))) {
            repository.save(product);
            return product.toString();
        }

        return "Product is already registered!";
    }


}
