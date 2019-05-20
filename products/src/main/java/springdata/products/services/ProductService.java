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
     * @param id
     * @return Product entity
     */
    public Product getProduct(String id) {

        Product product;
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        product = mongoTemplate.findOne(query, Product.class);

        return product;
    }

    /**
     * Add a product to the sortiment.
     *
     * @param product
     * @return String
     */
    @Override
    public void addProduct(Product product) {

        log.info("Log Product information for debugging purposes");
        log.info("==============================================");
        log.info("Product: " + product.getName() + ", " + product.getId() + ", "
                + product.getPrice() + ", " + product.getQuantity());
        log.info("==============================================");

        // Using upsert query for checking if object is already present in database
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(product.getName()));
        Update update = new Update();
        update.set("name", product.getName());
        update.set("price", product.getPrice());
        update.set("quantity", product.getQuantity());
        mongoTemplate.upsert(query, update, Product.class);

        log.info("check was successfull");
    }


}
