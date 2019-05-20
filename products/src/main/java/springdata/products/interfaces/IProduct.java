package springdata.products.interfaces;

import springdata.products.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProduct {

    /**
     * Returns a list of products.
     * @return List<Product>
     */
    List<Product> getAll();

    /**
     * Returns a single product.
     * @return Product
     */
    Product getProduct(String id);

    /**
     * Adds a product to the database.
     *
     * @param product
     * @return String
     */
     void addProduct(Product product);
}
