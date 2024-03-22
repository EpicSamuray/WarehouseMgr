package ch.hftm.model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import ch.hftm.model.order.Order;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

enum Role {
    ADMIN, USER
}

//TODO: add type same Products
@MongoEntity(collection = "user")
public class User extends PanacheMongoEntity {
    
    @BsonProperty("username")
    private String username;

    @BsonProperty("role")
    private Role role;

    @BsonProperty("email")
    private String email;

    @BsonProperty("order")
    private Order order;
}
