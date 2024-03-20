package ch.hftm.model;


import java.util.List;
import java.util.Objects;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

enum Status {
    PENDING, APPROVED, REJECTED
}

//TODO: add type same Products
@MongoEntity(collection = "order")
public class Order extends PanacheMongoEntity{
    
    @BsonProperty("orderDate")
    private String orderDate;

    @BsonProperty("status")
    private Status status;

    @BsonProperty("products")
    private List<ProductOrder> products;

    @BsonProperty("totalPrice")
    private double totalPrice;

    public Order() {
    }

    public Order(String orderDate, Status status, List<ProductOrder> products, double totalPrice) {
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.products = products;
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

    public List<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrder> products) {
        this.products = products;
    }
    
    public void addProduct(ProductOrder product) {
        this.products.add(product);
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
