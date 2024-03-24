package ch.hftm.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "warehouseLocation")
public class Location {

    @BsonProperty("_id")
    private ObjectId id;

    @BsonProperty("warehouseName")
    private String name;

    @BsonProperty("description")
    private String description;

    @BsonProperty("capacity")
    private int capacity;

    @BsonProperty("currentCapacity")
    private int currentCapacity;

    @BsonProperty("identifier")
    private String identifier;
}
