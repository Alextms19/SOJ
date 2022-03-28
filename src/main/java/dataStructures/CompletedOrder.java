package dataStructures;

public class CompletedOrder extends Order{
    private Driver driver;

    private String review;



    public CompletedOrder(Order order, double priceInRON) {
        super(order);

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



    @Override
    public String toString() {
        return "CompletedOrder{" +
                "order=" + super.toString() +
                ", driver=" + driver +
                ", review='" + review + '\'' +
                '}';
    }
}
