package ch.hftm.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import ch.hftm.model.Product;
import ch.hftm.repository.Interface.IRepositoryPersist;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product>, IRepositoryPersist{

    public boolean isPersisted(ObjectId id) {
        return findById(id) != null;
    }
}
