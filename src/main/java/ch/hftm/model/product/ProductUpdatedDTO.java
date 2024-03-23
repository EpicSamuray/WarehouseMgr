package ch.hftm.model.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdatedDTO {
    
    private String name;
    private String description;
    private double price;
    private String category;    
    
}
