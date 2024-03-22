package ch.hftm.model.order;

import java.util.List;

import ch.hftm.model.productOrder.ProductOrderCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO {

    private String orderDate;
    private List<ProductOrderCreateDTO> productOrders;
    
    
}
