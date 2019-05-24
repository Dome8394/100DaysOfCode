package springdata.products.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import springdata.products.entities.Product;
import springdata.products.interfaces.IProduct;

import java.util.List;

/**
 * Service implementation of the IProduct interface.
 *
 * @author Dominik
 * @date 01.05.2019
 */
@Component
public class ProductService implements IProduct {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * Queries a list of products from the database.
     *
     * @return List
     */
    @Override
    public List<Product> getAll() {
        return mongoTemplate.findAll(Product.class);
    }

    /**
     * Queries a single product entity from the database.
     *
     * @param id must not be null
     * @return Product must not be null
     */
    public Product getProduct(String id) {

        Query query = new Query();
        query.restrict(Product.class)                      // restrict the result set to instances of type Person
                .addCriteria(Criteria.where("id").is(id)); // returns documents instances where id equals parameter id

        Product product;
        product = mongoTemplate.findOne(query, Product.class);

        return product;
    }

    /**
     * Add a product to the sortiment.
     *
     * @param product must not be null
     */
    @Override
    public void addProduct(Product product) {

        log.info("Log Product information for debugging purposes");
        log.info("==============================================");
        log.info("Product: " + product.getName() + ", " + product.getId() + ", "
                + product.getPrice() + ", " + product.getQuantity());
        log.info("==============================================");

        /*
         * Using upsert query for checking if object is already present in database.
         * Criteria is added to only retrieve documents instances where names
         * equals the name of method parameter.
         *
         */
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(product.getName()));

        /*
        Create new update object and set properties of the object to be updated.
         */
        Update update = new Update();
        update.set("name", product.getName());
        update.set("price", product.getPrice());
        update.set("quantity", product.getQuantity());

        // update if object already exists, insert otherwise
        mongoTemplate.upsert(query, update, Product.class);
    }

    /**
     * Updates a given product's name.
     *
     * @param id   must not be null.
     * @param name must not be null.
     */
    @Override
    public void updateProductByName(String id, String name) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        // generate new update object
        Update update = new Update();
        update.set("name", name);


        // update object in database
        mongoTemplate.updateFirst(query, update, Product.class);

    }

    /**
     * Updates a given product's price.
     *
     * @param id    must not be null.
     * @param price must not be null.
     */
    @Override
    public void updateProductByPrice(String id, double price) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        // generate new update object
        Update update = new Update();
        update.set("price", price);


        // update object in database
        mongoTemplate.updateFirst(query, update, Product.class);
    }

    /**
     * Updates a given product's quantity.
     *
     * @param id       must not be null.
     * @param quantity must not be null.
     */
    @Override
    public void updateProductByQuantity(String id, int quantity) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        // generate new update object
        Update update = new Update();
        update.set("quantity", quantity);


        // update object in database
        mongoTemplate.updateFirst(query, update, Product.class);
    }

    /**
     * Removes a product from the database.
     *
     * @param product must not be null
     */
    @Override
    public void deleteProduct(Product product) {

        // new query object
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(product.getId()));

        mongoTemplate.remove(query, "products");

    }


}
