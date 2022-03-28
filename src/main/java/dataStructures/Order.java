package dataStructures;

import java.time.LocalDateTime;

public class Order {
    private LocalDateTime orderDateTime;

    private Client client;

    private String locationFrom;

    private String locationTo;

    private double distanceInKm;

    private double priceInRON;



    public Order(){

    }
    public Order(LocalDateTime orderDateTime,
                 Client client,
                 String locationFrom,
                 String locationTo,
                 double distanceInKm,
                 double priceInRON) {
        this.orderDateTime = orderDateTime;
        this.client = client;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.distanceInKm = distanceInKm;
        this.priceInRON = priceInRON;
    }

    public Order (Order order){
        this.orderDateTime = order.getOrderDateTime();
        this.client = order.getClient();
        this.locationFrom = order.getLocationFrom();
        this.locationTo = order.getLocationTo();
        this.distanceInKm = order.getDistanceInKm();
        this.priceInRON = order.getPriceInRON();
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(String locationFrom) {
        this.locationFrom = locationFrom;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public double getPriceInRON() {
        return priceInRON;
    }

    public void setPriceInRON(double priceInRON) {
        this.priceInRON = priceInRON;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDateTime=" + orderDateTime +
                ", client=" + client +
                ", locationFrom='" + locationFrom + '\'' +
                ", locationTo='" + locationTo + '\'' +
                ", distanceInKm=" + distanceInKm +
                ", priceInRON=" + priceInRON +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderDateTime.equals(order.orderDateTime) && client.equals(order.client) && locationFrom.equals(order.locationFrom) && locationTo.equals(order.locationTo);
    }

}
