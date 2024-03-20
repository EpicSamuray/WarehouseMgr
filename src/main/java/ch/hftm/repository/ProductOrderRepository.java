package ch.hftm.repository;

import ch.hftm.model.ProductOrder;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductOrderRepository implements PanacheMongoRepository<ProductOrder> {
    
}
