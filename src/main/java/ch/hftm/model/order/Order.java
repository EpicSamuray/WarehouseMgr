package ch.hftm.model.order;


import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import ch.hftm.model.productOrder.ProductOrder;
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
@MongoEntity(collection = "order")
public class Order extends PanacheMongoEntity{

    @BsonProperty("_id")
    private ObjectId id;
    
    @BsonProperty("orderDate")
    private String orderDate;

    @BsonProperty("status")
    private Status status;

    @BsonProperty("products")
    private List<ProductOrder> products;

    @BsonProperty("totalPrice")
    private double totalPrice;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

}
