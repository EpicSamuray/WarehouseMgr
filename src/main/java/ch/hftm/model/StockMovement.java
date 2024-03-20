package ch.hftm.model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

enum MovementType {
    IN, OUT
}

@MongoEntity(collection = "stockMovement")
public class StockMovement extends PanacheMongoEntity {
    
    @BsonProperty("productId")
    private Product product;

    @BsonProperty("type")
    private MovementType type;
    
    @BsonProperty("quantity")
    private int quantity;

    @BsonProperty("date")
    private String date;

    public StockMovement() {
    }

    public StockMovement(Product product, MovementType type, int quantity, String date) {
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
