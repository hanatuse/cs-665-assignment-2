package edu.bu.met.cs665.delivery;

import edu.bu.met.cs665.delivery.driver.Driver;
import edu.bu.met.cs665.delivery.model.DriverStatus;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String name;
    private List<Driver> drivers;

    public Shop(String name) {
        this.name = name;
        this.drivers = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

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
