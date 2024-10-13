package edu.bu.met.cs665.delivery;

import edu.bu.met.cs665.delivery.driver.VanDriver;
import edu.bu.met.cs665.delivery.driver.TaxiDriver;
import edu.bu.met.cs665.delivery.driver.ScooterDriver;
import edu.bu.met.cs665.delivery.driver.Driver;

public class Main {
    public static void main(String[] args) {
        // Create a shop
        Shop shop = new Shop("City Delivery Shop");

        // Create drivers with different max weight capacities
        Driver vanDriver1 = new VanDriver("John", 1000); // Van can carry 1000kg
        Driver vanDriver2 = new VanDriver("Michael", 1200); // Another van, can carry 1200kg
        Driver taxiDriver = new TaxiDriver("Emily", 500); // Taxi can carry 500kg
        Driver scooterDriver1 = new ScooterDriver("Alex", 50); // Scooter 1 can carry 50kg
        Driver scooterDriver2 = new ScooterDriver("Sophia", 30); // Scooter 2 can carry 30kg

        // Add drivers to the shop
        shop.addDriver(vanDriver1);
        shop.addDriver(vanDriver2);
        shop.addDriver(taxiDriver);
        shop.addDriver(scooterDriver1);
        shop.addDriver(scooterDriver2);

        // Create and broadcast delivery requests
        DeliveryRequest request1 = new DeliveryRequest("Smartphone", "123 Main St", 10);  // Small package
        shop.broadcastDeliveryRequest(request1);

        DeliveryRequest request2 = new DeliveryRequest("Furniture", "456 Elm St", 800);  // Heavy package
        shop.broadcastDeliveryRequest(request2);

        DeliveryRequest request3 = new DeliveryRequest("Laptop", "789 Pine St", 100);  // Medium package
        shop.broadcastDeliveryRequest(request3);
    }
}
