package edu.bu.met.cs665.delivery;

import edu.bu.met.cs665.delivery.driver.VanDriver;
import edu.bu.met.cs665.delivery.driver.TaxiDriver;
import edu.bu.met.cs665.delivery.driver.ScooterDriver;
import edu.bu.met.cs665.delivery.driver.Driver;
import edu.bu.met.cs665.delivery.model.DriverStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Shop test.
 */
public class ShopTest {

    private Shop shop;
    private Driver vanDriver1;
    private Driver vanDriver2;
    private Driver taxiDriver;
    private Driver scooterDriver1;
    private Driver scooterDriver2;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        // Create a new shop instance before each test
        shop = new Shop("City Delivery Shop");

        // Initialize drivers with different max weight capacities
        vanDriver1 = new VanDriver("John", 1000);
        vanDriver2 = new VanDriver("Michael", 1200);
        taxiDriver = new TaxiDriver("Emily", 500);
        scooterDriver1 = new ScooterDriver("Alex", 50);
        scooterDriver2 = new ScooterDriver("Sophia", 30);

        // Add drivers to the shop
        shop.addDriver(vanDriver1);
        shop.addDriver(vanDriver2);
        shop.addDriver(taxiDriver);
        shop.addDriver(scooterDriver1);
        shop.addDriver(scooterDriver2);
    }

    /**
     * Test broadcast to all drivers.
     */
    @Test
    public void testBroadcastToAllDrivers() {
        // Small delivery request (5kg), should be assigned to scooter driver 2 (since it's the smallest available capacity)
        DeliveryRequest request = new DeliveryRequest("Phone", "123 Main St", 5);

        // Broadcast the request
        shop.broadcastDeliveryRequest(request);

        // Assert that scooter driver 2 is assigned (since it has the smallest max weight of 30kg)
        assertEquals(DriverStatus.BUSY, scooterDriver2.getStatus(), "Scooter driver 2 should be assigned.");
        assertEquals(DriverStatus.AVAILABLE, scooterDriver1.getStatus(), "Scooter driver 1 should remain available.");
        assertEquals(DriverStatus.AVAILABLE, vanDriver1.getStatus(), "Van driver 1 should remain available.");
    }

    /**
     * Test van driver assigned heavy delivery.
     */
    @Test
    public void testVanDriverAssignedHeavyDelivery() {
        // Heavy delivery request (800kg), should be assigned to van driver 1 since only vans can carry this weight
        DeliveryRequest request = new DeliveryRequest("Furniture", "456 Elm St", 800);

        // Broadcast the request
        shop.broadcastDeliveryRequest(request);

        // Assert that van driver 1 is assigned
        assertEquals(DriverStatus.BUSY, vanDriver1.getStatus(), "Van driver 1 should be assigned.");
        assertEquals(DriverStatus.AVAILABLE, scooterDriver1.getStatus(), "Scooter driver 1 should remain available.");
        assertEquals(DriverStatus.AVAILABLE, taxiDriver.getStatus(), "Taxi driver should remain available.");
    }

    /**
     * Test no driver available for heavy delivery.
     */
    @Test
    public void testNoDriverAvailableForHeavyDelivery() {
        // Delivery request that's too heavy for any driver (1500kg)
        DeliveryRequest request = new DeliveryRequest("Heavy Machinery", "789 Pine St", 1500);

        // Broadcast the request
        shop.broadcastDeliveryRequest(request);

        // Assert that no driver was assigned since none can handle the weight
        assertEquals(DriverStatus.AVAILABLE, vanDriver1.getStatus(), "Van driver 1 should remain available.");
        assertEquals(DriverStatus.AVAILABLE, vanDriver2.getStatus(), "Van driver 2 should remain available.");
        assertEquals(DriverStatus.AVAILABLE, scooterDriver1.getStatus(), "Scooter driver 1 should remain available.");
        assertEquals(DriverStatus.AVAILABLE, taxiDriver.getStatus(), "Taxi driver should remain available.");
    }

    /**
     * Test multiple requests.
     */
    @Test
    public void testMultipleRequests() {
        // First request (10kg), should be assigned to scooter driver 2 (smallest capacity that can handle it)
        DeliveryRequest request1 = new DeliveryRequest("Laptop", "101 First St", 10);
        shop.broadcastDeliveryRequest(request1);
        assertEquals(DriverStatus.BUSY, scooterDriver2.getStatus(), "Scooter driver 2 should be assigned.");

        // Second request (25kg), should be assigned to scooter driver 1 (next smallest capacity)
        DeliveryRequest request2 = new DeliveryRequest("Tablet", "102 First St", 25);
        shop.broadcastDeliveryRequest(request2);
        assertEquals(DriverStatus.BUSY, scooterDriver1.getStatus(), "Scooter driver 1 should be assigned.");

        // Third request (700kg), should be assigned to van driver 1 (next smallest capacity that can handle the weight)
        DeliveryRequest request3 = new DeliveryRequest("Fridge", "103 First St", 700);
        shop.broadcastDeliveryRequest(request3);
        assertEquals(DriverStatus.BUSY, vanDriver1.getStatus(), "Van driver 1 should be assigned.");
    }

    /**
     * Test driver status reset.
     */
    @Test
    public void testDriverStatusReset() {
        // Create a delivery request and assign it to scooter driver 2
        DeliveryRequest request = new DeliveryRequest("Smartphone", "789 Main St", 5);
        shop.broadcastDeliveryRequest(request);

        // Verify that scooter driver 2 is now BUSY
        assertEquals(DriverStatus.BUSY, scooterDriver2.getStatus(), "Scooter driver 2 should be busy.");

        // Manually set the driver's status back to AVAILABLE (simulating delivery completion)
        scooterDriver2.setStatus(DriverStatus.AVAILABLE);

        // Verify that the driver is now available again
        assertEquals(DriverStatus.AVAILABLE, scooterDriver2.getStatus(), "Scooter driver 2 should be available again.");
    }
}
