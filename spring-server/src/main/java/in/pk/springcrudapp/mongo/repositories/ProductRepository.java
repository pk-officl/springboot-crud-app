package in.pk.springcrudapp.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.pk.springcrudapp.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
