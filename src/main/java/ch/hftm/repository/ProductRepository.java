package ch.hftm.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;

import ch.hftm.model.product.Product;
import ch.hftm.repository.Interface.IRepositoryPersist;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product>, IRepositoryPersist{

    public boolean isPersisted(ObjectId id) {
        return findById(id) != null;
    }

    public List<Product> findAllProducts(int page, int pageSize) {
        return find("from Product", new HashMap<>(), page, pageSize).list();
    }

    public Long countAll() {
        return count("from Product");
    }
}
