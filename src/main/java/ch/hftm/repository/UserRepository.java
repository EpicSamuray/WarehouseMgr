package ch.hftm.repository;

import org.bson.types.ObjectId;

import ch.hftm.model.User;
import ch.hftm.repository.Interface.IRepositoryPersist;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User>, IRepositoryPersist {

    @Override
    public boolean isPersisted(ObjectId objectId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPersisted'");
    }
    
}
