package ch.hftm.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Objects;

@MongoEntity(collection = "product")
public class Product extends PanacheMongoEntity {

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

    @BsonProperty("validated")
    private boolean validation;

    public Product() {

    }

   

    public Product( String name, String description, double price, String category, int quantity, boolean validation) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.validation = validation;
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



    public boolean isValidation() {
        return validation;
    }



    public void setValidation(boolean validation) {
        this.validation = validation;
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
