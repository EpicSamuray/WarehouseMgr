package ch.hftm.repository;

import org.bson.types.ObjectId;

import ch.hftm.model.order.Order;
import ch.hftm.repository.Interface.IRepositoryPersist;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<Order>, IRepositoryPersist {

    @Override
    public boolean isPersisted(ObjectId objectId) {
        return findById(objectId) != null;
    }

    public Long countAll() {
        return count("from Order");
    }
    
}
