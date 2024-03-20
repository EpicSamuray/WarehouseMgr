package ch.hftm.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import ch.hftm.model.Product;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product> {

    public boolean isPersistend(ObjectId id) {
        return findById(id) != null;
    }
}
