package ch.hftm.repository;

import org.bson.types.ObjectId;

import ch.hftm.model.Location;
import ch.hftm.repository.Interface.IRepositoryPersist;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

public class LocationRepository implements PanacheMongoRepository<Location>, IRepositoryPersist {

    @Override
    public boolean isPersisted(ObjectId objectId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPersisted'");
    }
    
}
