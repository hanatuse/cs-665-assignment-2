package edu.bu.met.cs665.delivery.driver;

import edu.bu.met.cs665.delivery.DeliveryRequest;
import edu.bu.met.cs665.delivery.model.DriverStatus;
import edu.bu.met.cs665.delivery.model.VehicleType;

/**
 * Represents a driver for a scooter delivery service.
 *
 * @author yingtongzhou
 */
public class ScooterDriver implements Driver {
    private String name;
    private DriverStatus status;
    private double maxWeight;

    /**
     * Instantiates a new Scooter driver.
     *
     * @param name      the name of the driver
     * @param maxWeight the max weight the scooter driver can carry
     */
    public ScooterDriver(String name, double maxWeight) {
        this.name = name;
        /**
         * The status of the driver (available, busy, etc.).
         */
        this.status = DriverStatus.AVAILABLE;
        this.maxWeight = maxWeight;
    }

    @Override
    public void notify(DeliveryRequest request) {
        System.out.println("Scooter Driver " + name + " received a delivery request: " + request.getDetails());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.SCOOTER;
    }

    @Override
    public DriverStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    @Override
    public double getMaxWeight() {
        return maxWeight;
    }
}
