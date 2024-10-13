package edu.bu.met.cs665.delivery;

import edu.bu.met.cs665.delivery.driver.Driver;
import edu.bu.met.cs665.delivery.model.DriverStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * The process of shop and delivery
 *
 * @author yingtongzhou
 */
public class Shop {
    private String name;
    private List<Driver> drivers;

    /**
     * Instantiates a new Shop process.
     *
     * @param name the name
     */
    public Shop(String name) {
        this.name = name;
        this.drivers = new ArrayList<>();
    }

    /**
     * Add a driver.
     *
     * @param driver the driver
     */
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    /**
     * Broadcast delivery request.
     *
     * @param request the request
     */
    public void broadcastDeliveryRequest(DeliveryRequest request) {
        Driver assignedDriver = null;

        // Sort drivers based on their max weight capacity (smaller vehicles first)
        drivers.sort((d1, d2) -> Double.compare(d1.getMaxWeight(), d2.getMaxWeight()));

        for (Driver driver : drivers) {
            if (driver.getStatus() == DriverStatus.AVAILABLE && driver.getMaxWeight() >= request.getWeight()) {
                driver.setStatus(DriverStatus.BUSY);
                driver.notify(request);
                assignedDriver = driver;
                break;  // Assign the first available driver that can handle the weight
            }
        }

        if (assignedDriver == null) {
            System.out.println("No available drivers for the delivery of " + request.getDetails());
        } else {
            System.out.println(assignedDriver.getName() + " is now assigned to this delivery.");
        }
    }
}
