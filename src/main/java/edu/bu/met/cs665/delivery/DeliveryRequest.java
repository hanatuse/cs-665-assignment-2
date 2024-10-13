package edu.bu.met.cs665.delivery;

/**
 * Represents the details of Delivery request.
 *
 * @author yingtongzhou
 */
public class DeliveryRequest {
    private String product;
    private String destination;
    private double weight;

    /**
     * Instantiates a new Delivery request.
     *
     * @param product the product the driver has to deliver
     * @param destination the delivery destination
     * @param weight the weight of the product
     */
    public DeliveryRequest(String product, String destination, double weight) {
        this.product = product;
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return "Product: " + product + ", Destination: " + destination + ", Weight: " + weight + "kg";
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }
}
