package ch.hftm.model.product;

import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Type;

import ch.hftm.model.product.util.StockMovementCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Type
public class ProductCreateDTO {

    @NonNull private String name;
    private String description;
    @NonNull private double price;
    @NonNull private String category;
    @NonNull private StockMovementCreateDTO stockMovements;
    @NonNull private String newestLocationId;
}
