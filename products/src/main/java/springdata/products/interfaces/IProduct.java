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
     * @param product must not be null.
     */
     void addProduct(Product product);

    /**
     * Updates information of an existing product
     *
     * @param id must not be null.
     * @param name must not be null.
     */
    void updateProductByName(String id, String name);

    /**
     * Updates information of an existing product
     *
     * @param id must not be null.
     * @param price must not be null.
     */
    void updateProductByPrice(String id, double price);

    /**
     * Updates information of an existing product
     *
     * @param id must not be null.
     * @param quantity must not be null.
     */
    void updateProductByQuantity(String id, int quantity);

    void deleteProduct(Product product);
}
