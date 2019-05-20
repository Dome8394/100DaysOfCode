package springdata.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import springdata.products.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
