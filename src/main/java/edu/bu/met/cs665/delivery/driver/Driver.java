package edu.bu.met.cs665.delivery.driver;

import edu.bu.met.cs665.delivery.DeliveryRequest;
import edu.bu.met.cs665.delivery.model.DriverStatus;
import edu.bu.met.cs665.delivery.model.VehicleType;

public interface Driver {
    void notify(DeliveryRequest request);
    String getName();
    VehicleType getVehicleType();
    DriverStatus getStatus();
    void setStatus(DriverStatus status);
    double getMaxWeight(); // Getter for the max weight
}
