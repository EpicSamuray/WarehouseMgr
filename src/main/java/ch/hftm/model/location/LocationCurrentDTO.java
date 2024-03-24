package ch.hftm.model.location;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class LocationCurrentDTO {
    
    private ObjectId id;
    private String name;
    private String identifier;
}
