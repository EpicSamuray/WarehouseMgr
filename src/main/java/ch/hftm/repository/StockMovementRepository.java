package ch.hftm.repository;

import ch.hftm.model.StockMovement;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StockMovementRepository implements PanacheMongoRepository<StockMovement> {
    
}
