package dataStructures;

public class Client extends User {
    private String address;
    private String phoneNumber;


    public Client(String username, String password) {
        super(username, password);
    }
    public Client (Client client){
        super(client.username, client.password);
        this.address = client.address;
        this.phoneNumber = client.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Client { " + username + " } ";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Client) {
            Client tmp = (Client) o;
            return tmp.username.equals(this.username);
        } else
            return false;
    }
}