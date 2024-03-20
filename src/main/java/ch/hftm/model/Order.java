package ch.hftm.model;


import java.util.Objects;

import org.bson.codecs.pojo.annotations.BsonProperty;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

enum Status {
    PENDING, APPROVED, REJECTED
}

@MongoEntity(collection = "order")
public class Order extends PanacheMongoEntity{
    
    @BsonProperty("orderDate")
    private String orderDate;

    @BsonProperty("status")
    private Status status;

    @BsonProperty("products")
    private ProductOrder[] products;

    @BsonProperty("totalPrice")
    private double totalPrice;

    public Order() {
    }

    public Order(String orderDate, Status status, ProductOrder[] products, double totalPrice) {
        this.orderDate = orderDate;
        this.status = status;
        this.products = products;
        this.totalPrice = totalPrice;
    }



    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ProductOrder[] getProducts() {
        return products;
    }

    public void setProducts(ProductOrder[] products) {
        this.products = products;
    }
    
    public void addProduct(ProductOrder product) {
        ProductOrder[] newProducts = new ProductOrder[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
        newProducts[products.length] = product;
        this.products = newProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
