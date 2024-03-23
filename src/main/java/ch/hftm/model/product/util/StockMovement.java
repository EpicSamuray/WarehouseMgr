package ch.hftm.model.product.util;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: add type same Products
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "stockMovement")
public class StockMovement extends PanacheMongoEntity {

    @BsonProperty("_id")
    private ObjectId id;

    @BsonProperty("type")
    private MovementType type;
    
    @BsonProperty("quantity")
    private int quantity;

    @BsonProperty("date")
    private String date;

    @BsonProperty("productId")
    private ObjectId productId;

    public enum MovementType {
        IN, OUT
    }

}
