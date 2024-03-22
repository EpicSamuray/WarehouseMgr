package ch.hftm.model.product;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import ch.hftm.model.product.util.StockMovement;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "product")
public class Product extends PanacheMongoEntity {

    @BsonProperty("_id")
    private ObjectId id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("description")
    private String description;
    
    @BsonProperty("price")
    private double price;

    @BsonProperty("category")
    private String category;
    
    @BsonProperty("quantity")
    private int quantity;

    @BsonProperty("StockMovement")
    private List<StockMovement> stockMovements;



}
