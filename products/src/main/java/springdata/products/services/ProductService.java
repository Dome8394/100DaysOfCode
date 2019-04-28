package springdata.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springdata.products.entities.Product;
import springdata.products.interfaces.IProduct;
import springdata.products.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getProduct(String id) {
        return repository.findById(id);
    }
}
