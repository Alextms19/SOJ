package dataStructures;

public class CompletedOrder extends Order{
    private Driver driver;
    private String review;
    private double distanceInKm;
    private double priceInRON;
    private Order order;

    public CompletedOrder(){
        super();
    }
    public CompletedOrder(Order order, double priceInRON, double distanceInKm, Driver driver) {
        super(order);
        this.order = order;
        this.driver = driver;
        this.distanceInKm = distanceInKm;
        this.priceInRON = priceInRON;
        setPriceInRON(priceInRON);

    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CompletedOrder{" +
                "order=" + super.toString() +
                ", driver=" + driver +
                ", review='" + review + '\'' +
                '}';
    }
}
