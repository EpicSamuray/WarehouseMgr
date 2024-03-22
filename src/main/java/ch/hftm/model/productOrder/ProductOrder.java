package ch.hftm.model.productOrder;

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
@MongoEntity(collection = "productOrder")
public class ProductOrder extends PanacheMongoEntity {

    @BsonProperty("_id")
    private ObjectId id;
    
    @BsonProperty("product")
    private ObjectId productId;

    @BsonProperty("order")
    private ObjectId orderId;

    @BsonProperty("quantity")
    private int quantity;

    @BsonProperty("priceAtOrder")
    private double priceAtOrder;
    
}
