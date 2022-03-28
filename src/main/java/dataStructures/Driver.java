package dataStructures;

public class Driver extends User {
    private String licencePlate;
    private String carModel;

    public Driver(String username, String password) {
        super(username, password);
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Driver) {
            Driver aux = (Driver) o;
            return aux.getUsername().equals(this.getUsername());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sofer{" +
                "numarInmatriculare='" + licencePlate + '\'' +
                ", masina='" + carModel + '\'' +
                '}';
    }
}