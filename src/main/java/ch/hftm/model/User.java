package ch.hftm.model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

enum Role {
    ADMIN, USER
}

@MongoEntity(collection = "user")
public class User extends PanacheMongoEntity {
    
    @BsonProperty("username")
    private String username;

    @BsonProperty("role")
    private Role role;

    @BsonProperty("email")
    private String email;
}
