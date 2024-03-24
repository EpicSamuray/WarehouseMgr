package ch.hftm.resource;

import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import ch.hftm.model.location.Location;
import ch.hftm.model.location.LocationCreateDTO;
import ch.hftm.services.LocationService;
import io.vertx.core.cli.annotations.Description;
import jakarta.inject.Inject;

@GraphQLApi
public class LocationResource {

    @Inject
    private LocationService locationService;
    
    private static final Logger LOG = Logger.getLogger(LocationResource.class);

    @Mutation
    @Description("Create a new location")
    public Location createLocation(LocationCreateDTO location) {
        LOG.info("Creating new location: " + location.getName());
        Location newLocation = locationService.createLocation(location);
        if (newLocation != null) {
            LOG.info("Sending location for validation: " + newLocation.getId());
            return newLocation;
        }
        return null;
    }

    @Mutation
    @Description("Update a location")
    public Location updateLocation(String id, Location location) {
        LOG.info("Updating location: " + id);
        try {
            return locationService.updateLocation(id, location);
        } catch (Exception e) {
            LOG.error("Error updating location: " + id + " | " + e.getMessage());
            return null;
        }
    }

    @Mutation
    @Description("Delete a location")
    public boolean deleteLocationById(String id) {
        LOG.info("Deleting location: " + id);
        return locationService.deleteLocation(new ObjectId(id));
    }

    @Query
    @Description("Get all locations")
    public List<Location> allLocations() {
        LOG.info("Getting all locations");
        return locationService.getAllLocations();
    }

    @Query
    @Description("Get location by ID")
    public Location locationById(String id) {
        LOG.info("Getting location by ID: " + id);
        return locationService.getLocationById(new ObjectId(id));
    }

    @Query
    @Description("Get location by identifier")
    public Location locationByIdentifier(String identifier) {
        LOG.info("Getting location by identifier: " + identifier);
        return locationService.getLocationByIdentifier(identifier);
    }
}
