package ch.hftm.repository;

import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import ch.hftm.model.location.Location;
import ch.hftm.repository.Interface.IRepositoryPersist;
import ch.hftm.resource.LocationResource;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LocationRepository implements PanacheMongoRepository<Location>, IRepositoryPersist {

    private static final Logger LOG = Logger.getLogger(LocationResource.class);


    @Override
    public boolean isPersisted(ObjectId objectId) {
        LOG.info("Checking if location exists: " + objectId);
        return findById(objectId) != null;
    }

    public Boolean existLocationByIdentifier(String identifier) {
        LOG.info("Checking if location exists: " + identifier);
        PanacheQuery<Location> existingLocation = find("identifier", identifier);
        LOG.info("Location count: " + existingLocation.count());
        
        if (existingLocation.count() == 0) {
            return false;
        } 
        return true;
    }

    public Location findByIdentifier(String identifier) {
        PanacheQuery<Location> location = find("identifier", identifier);
        if (location.count() == 0) {
            return null;
        }
        return location.firstResult();
    }
    
}
