package springdata.products.interfaces;

import springdata.products.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProduct {

    /**
     * Returns a list of products.
     * @return List<Product>
     */
    public List<Product> getAll();

    /**
     * Returns a single product.
     * @return Product
     */
    public Optional<Product> getProduct(String id);
}
