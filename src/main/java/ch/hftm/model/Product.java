package ch.hftm.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.Min;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Type;

import java.util.List;
import java.util.Objects;

@MongoEntity(collection = "product")
@Type
public class Product extends PanacheMongoEntity {

    @BsonProperty("name")
    @NonNull
    private String name;

    @BsonProperty("description")
    private String description;
    
    @BsonProperty("price")
    @NonNull
    private double price;

    @BsonProperty("category")
    @NonNull
    private String category;
    
    @BsonProperty("quantity")
    @Min(1)
    private int quantity;

    @BsonProperty("StockMovement")
    private List<StockMovement> stockMovements;

    public Product() {

    }

   

    public Product( String name, String description, double price, String category, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    


    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public double getPrice() {
        return price;
    }



    public void setPrice(double price) {
        this.price = price;
    }



    public String getCategory() {
        return category;
    }



    public void setCategory(String category) {
        this.category = category;
    }



    public int getQuantity() {
        return quantity;
    }



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product other)) {
            return false;
        }

        return Objects.equals(other.id, this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
