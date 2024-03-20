package ch.hftm.repository.Interface;

import org.bson.types.ObjectId;

public interface IRepositoryPersist {
    
    boolean isPersisted(ObjectId objectId);

}
