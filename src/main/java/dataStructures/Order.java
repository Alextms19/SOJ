package dataStructures;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private LocalDateTime orderDateTime;

    private Client client;

    private String locationFrom;

    private String locationTo;


    public Order(){

    }
    public Order(LocalDateTime orderDateTime,
                 Client client,
                 String locationFrom,
                 String locationTo
               ) {
        this.orderDateTime = orderDateTime;
        this.client = client;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;

    }

    public Order (Order order){
        this.orderDateTime = order.getOrderDateTime();
        this.client = order.getClient();
        this.locationFrom = order.getLocationFrom();
        this.locationTo = order.getLocationTo();

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


    @Override
    public String toString() {
        return "Client " + client.getUsername().toUpperCase() +
                ", From " + locationFrom +
                ", To " + locationTo +
                ", Time " + orderDateTime.getHour() +":"+ orderDateTime.getMinute() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(orderDateTime, order.orderDateTime) && Objects.equals(client, order.client) && Objects.equals(locationFrom, order.locationFrom) && Objects.equals(locationTo, order.locationTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDateTime, client, locationFrom, locationTo);
    }
}
