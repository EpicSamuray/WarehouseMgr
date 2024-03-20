package ch.hftm.model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

enum MovementType {
    IN, OUT
}

//TODO: add type same Products
@MongoEntity(collection = "stockMovement")
public class StockMovement extends PanacheMongoEntity {

    @BsonProperty("type")
    private MovementType type;
    
    @BsonProperty("quantity")
    private int quantity;

    @BsonProperty("date")
    private String date;

    public StockMovement() {
    }

    public StockMovement(MovementType type, int quantity, String date) {
        this.type = type;
        this.quantity = quantity;
        this.date = date;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
}
