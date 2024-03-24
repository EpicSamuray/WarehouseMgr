package ch.hftm.model.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationCreateDTO {
    
    private String name;
    private String description;
    private int maxCapacity;
    private String identifier;

}
