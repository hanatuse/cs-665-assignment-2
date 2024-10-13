package edu.bu.met.cs665.delivery;

public class DeliveryRequest {
    private String product;
    private String destination;
    private double weight;

    public DeliveryRequest(String product, String destination, double weight) {
        this.product = product;
        this.destination = destination;
        this.weight = weight;
    }

    public String getDetails() {
        return "Product: " + product + ", Destination: " + destination + ", Weight: " + weight + "kg";
    }

    public double getWeight() {
        return weight;
    }
}
