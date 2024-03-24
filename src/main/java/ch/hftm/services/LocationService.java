package ch.hftm.services;

import java.util.List;

import org.apache.kafka.common.protocol.types.Field.Str;
import org.bson.types.ObjectId;

import ch.hftm.model.location.Location;
import ch.hftm.model.location.LocationCreateDTO;
import ch.hftm.repository.LocationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LocationService {
    
    @Inject
    private LocationRepository locationRepository;

    public Location createLocation(LocationCreateDTO location) {
        Location locationToCreate = mapLocationCreateDTOToLocation(location);
        if (locationRepository.existLocationByIdentifier(location.getIdentifier())){
            throw new IllegalArgumentException("Location with identifier " + location.getIdentifier() + " already exists");
        }
        locationRepository.persist(locationToCreate);
        return locationRepository.findById(locationToCreate.getId());
    }

    public Location existsIdentifier(String identifier) {
        return locationRepository.findByIdentifier(identifier);
    }

    public Location updateLocation(String id, Location location) {
        if (!locationRepository.isPersisted(new ObjectId(id))) {
            throw new IllegalArgumentException("Location does not exist");
        }
        Location locationToUpdate = locationRepository.findById(new ObjectId(id));
        locationToUpdate.setName(location.getName());
        locationToUpdate.setDescription(location.getDescription());
        locationToUpdate.setCapacity(location.getCapacity());
        locationToUpdate.setIdentifier(location.getIdentifier());
        locationToUpdate.setCurrentCapacity(location.getCurrentCapacity());
        locationRepository.update(location);
        return locationRepository.findById(location.getId());
    }

    public boolean deleteLocation(ObjectId id) {
        return locationRepository.deleteById(id);
    }

    public Location getLocationById(ObjectId id) {
        return locationRepository.findById(id);
    }

    public Location getLocationByIdentifier(String identifier) {
        return locationRepository.findByIdentifier(identifier);
    }

    public List<Location> getAllLocations() {
        return locationRepository.listAll();
    }

    private Location mapLocationCreateDTOToLocation(LocationCreateDTO locationCreateDTO) {
        Location location = new Location();
        location.setId(new ObjectId());
        location.setName(locationCreateDTO.getName());
        location.setDescription(locationCreateDTO.getDescription());
        location.setCapacity(locationCreateDTO.getMaxCapacity());
        location.setIdentifier(locationCreateDTO.getIdentifier());
        location.setCurrentCapacity(0);
        return location;
    }
}
